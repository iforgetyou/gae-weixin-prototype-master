package com.zy17.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.zy17.model.Base;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhangyan53 on 2016/3/11.
 */
@Slf4j
public class BaseDaoImpl<T extends Base> {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    //    public T save(T obj) {
    //        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
    //        try {
    //            T result = pm.makePersistent(obj);
    //            return result;
    //        } finally {
    //            pm.close();
    //        }
    //    }

    public Key save(T obj) {
        Key key = null;
        try {
            Entity entity = obj.genEntity();
            key = datastore.put(entity);
            log.debug(entity.toString());
        } catch (IllegalAccessException e) {
            log.error("save entity error:", e);
        }
        return key;
    }

    public List<T> find(Query.Filter filter) {
        Query q = new Query(getType().getSimpleName()).setFilter(filter);
        log.debug("query:", q.toString());
        PreparedQuery pq = datastore.prepare(q);
        Iterable<Entity> entities = pq.asIterable();
        ArrayList<T> results = new ArrayList<>();

        for (Entity entity : entities) {
            try {
                T obj = parseEntity(entity);
                results.add(obj);
            } catch (Exception e) {
                log.error("find entity error", e);
            }
        }
        return results;
    }

    // 通过id查找
    public T findById(String id) {
        Key key = KeyFactory.createKey(getType().getSimpleName(), Long.valueOf(id));
        Query.Filter keyFilter =
                new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY,
                        Query.FilterOperator.EQUAL,
                        key);
        Query q =
                new Query(getType().getSimpleName()).setFilter(keyFilter).addSort("ID", Query.SortDirection.DESCENDING);
        log.debug("query:{}", q.toString());
        PreparedQuery pq = datastore.prepare(q);
        Entity entity = pq.asSingleEntity();
        if (entity == null) {
            return null;
        }
        return parseEntity(entity);
    }

    public T parseEntity(Entity entity) {
        log.debug(entity.toString());
        T t = null;
        try {
            t = (T) getType().newInstance();
            Field[] declaredFields = getType().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                declaredField.set(t, entity.getProperty(declaredField.getName()));
            }
        } catch (Exception e) {
            log.error("parse entity error", e);
        }
        return t;
    }

    public Class<?> getType() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
