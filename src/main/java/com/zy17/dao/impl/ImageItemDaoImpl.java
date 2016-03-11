package com.zy17.dao.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.zy17.dao.ImageItemDao;
import com.zy17.model.ImageItem;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhangyan53 on 2016/3/11.
 */
@Slf4j
@Repository
public class ImageItemDaoImpl extends BaseDaoImpl<ImageItem> implements ImageItemDao {

    @Override
    public ImageItem save(ImageItem imageItem) {
        return super.save(imageItem);
    }

    @Override
    public List<ImageItem> findOneByTag(String tag) {
        Query.Filter heightMaxFilter =
                new Query.FilterPredicate("textTag",
                        Query.FilterOperator.EQUAL,
                        tag);
        List<ImageItem> imageItems = super.find(heightMaxFilter);
        return imageItems;
    }
}
