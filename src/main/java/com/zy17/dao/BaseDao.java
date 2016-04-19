package com.zy17.dao;

import com.google.appengine.api.datastore.Key;

/**
 * Created by zhangyan53 on 2016/3/18.
 */
public interface BaseDao<T> {
    Key save(T imageEntity);
    void asyncSave(T imageEntity);
    T findOneById(long id);
}
