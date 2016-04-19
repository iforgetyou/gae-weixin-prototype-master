package com.zy17.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.zy17.dao.ImageItemDao;
import com.zy17.entity.ImageItem;
import com.zy17.service.googleservice.CacheService;

import lombok.extern.slf4j.Slf4j;

/**
 * 图片存储
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
@Repository
public class ImageItemDaoImpl extends BaseDaoImpl<ImageItem> implements ImageItemDao {

    @Override
    public Key save(ImageItem imageItem) {
        // 缓存Image
        Key save = super.save(imageItem);
        return save;
    }

    @Override
    public void asyncSave(ImageItem imageItem) {
        // 异步存储
        super.asyncSave(imageItem);
    }

    @Override
    public List<ImageItem> findOneByTag(String tag) {
        Query.Filter textFlagFilter =
                new Query.FilterPredicate("textTag",
                        Query.FilterOperator.EQUAL,
                        tag);

        List<ImageItem> imageEntities = super.find(textFlagFilter);
        return imageEntities;
    }

    @Override
    public List<Long> findAllValuableId() {
        // 暂时只查找临时资源
        Query.Filter weixinMaterialFilter =
                new Query.FilterPredicate("weixinMaterial",
                        Query.FilterOperator.EQUAL,
                        false);
        // 3天内资源
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        Query.Filter tempMaterialFilter =
                new Query.FilterPredicate("createdAt",
                        Query.FilterOperator.GREATER_THAN,
                        calendar.getTime());
        Query.CompositeFilter and = Query.CompositeFilterOperator.and(weixinMaterialFilter, tempMaterialFilter);

        Query q = new Query(getType().getSimpleName()).setFilter(and).setKeysOnly();
        PreparedQuery pq = datastore.prepare(q);
        List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
        List<Long> ids = new ArrayList<>();
        for (Entity result : results) {
            ids.add(result.getKey().getId());
        }
        return ids;
    }

    @Override
    public List<Long> findAllId() {
        Query q = new Query(getType().getSimpleName()).setKeysOnly();
        PreparedQuery pq = datastore.prepare(q);
        List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
        List<Long> ids = new ArrayList<>();
        for (Entity result : results) {
            ids.add(result.getKey().getId());
        }
        return ids;
    }

    @Override
    public ImageItem findOneById(long id) {
        // 优先读缓存
        ImageItem imageItem = (ImageItem) cacheService.getCache().get(CacheService.IMAGE_ENTITY + id);
        if (imageItem != null) {
            return imageItem;
        }
        // 缓存无,找DB
        ImageItem dbImage = super.findById(id);
        if (dbImage != null) {
            cacheImage(dbImage);
        }
        return dbImage;
    }

    private void cacheImage(ImageItem imageItem) {
        cacheService.getCache().put(CacheService.IMAGE_ENTITY + imageItem.getID(), imageItem);
    }
}
