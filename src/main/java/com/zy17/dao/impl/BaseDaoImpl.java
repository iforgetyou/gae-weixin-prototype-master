package com.zy17.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Projection;
import com.google.appengine.api.datastore.Query;
import com.zy17.entity.BaseEntity;
import com.zy17.service.googleservice.CacheService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class BaseDaoImpl<T extends BaseEntity> {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    AsyncDatastoreService asyncDatastore = DatastoreServiceFactory.getAsyncDatastoreService();
    @Autowired
    CacheService cacheService;

    public Key save(T obj) {
        Key key = null;
        try {
            Entity entity = obj.genEntity();
            key = datastore.put(entity);
            obj.setID(key.getId());
            log.debug("save obj:{},entity:{}", JSON.toJSONString(obj), entity.toString());
        } catch (IllegalAccessException e) {
            log.error("save entity error:", e);
        }
        return key;
    }

    public void asyncSave(T obj) {
        try {
            Entity entity = obj.genEntity();
            Future<Key> put = asyncDatastore.put(entity);
            log.debug("async save entity:{}", entity.toString());
        } catch (IllegalAccessException e) {
            log.error("async save entity error:", e);
        }
    }

    public List<T> find(Query.Filter filter) {
        PreparedQuery pq = preparedQuery(filter);
        Iterable<Entity> entities = pq.asIterable();
        ArrayList<T> results = new ArrayList<>();

        for (Entity entity : entities) {
            try {
                T obj = (T) BaseEntity.parseEntity(entity.getProperties(), entity.getKey().getId(), getType());
                if (obj != null) {
                    results.add(obj);
                }
            } catch (Exception e) {
                log.error("find entity error", e);
            }
        }
        return results;
    }

    public T findOne(Query.Filter filter, Projection... projections) {
        PreparedQuery pq = preparedQuery(filter);
        Entity entity = pq.asSingleEntity();
        T obj = (T) BaseEntity.parseEntity(entity.getProperties(), entity.getKey().getId(), getType());
        return obj;
    }

    protected PreparedQuery preparedQuery(Query.Filter filter, Projection... projections) {
        Query q = new Query(getType().getSimpleName()).setFilter(filter);
        for (Projection projection : projections) {
            q.addProjection(projection);
        }
        log.debug("query:", q.toString());
        PreparedQuery pq = datastore.prepare(q);
        return pq;
    }

    // 通过id查找
    public T findById(long id) {
        Key key = KeyFactory.createKey(getType().getSimpleName(), id);
        Query.Filter keyFilter =
                new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY,
                        Query.FilterOperator.EQUAL,
                        key);
        Query q =
                new Query(getType().getSimpleName()).setFilter(keyFilter);
        log.debug("query:{}", q.toString());
        PreparedQuery pq = datastore.prepare(q);
        Entity entity = pq.asSingleEntity();
        if (entity == null) {
            return null;
        }
        return BaseEntity.parseEntity(entity.getProperties(), entity.getKey().getId(), getType());
    }

    //    public T parseEntity(Entity entity) {

    public Class<T> getType() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
