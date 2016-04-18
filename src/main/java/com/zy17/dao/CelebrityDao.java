package com.zy17.dao;

import com.google.appengine.api.datastore.Key;
import com.zy17.entity.CelebrityEntity;

/**
 * Created by zy17 on 2016/3/19.
 */
public interface CelebrityDao extends BaseDao<CelebrityEntity> {
    @Override
    Key save(CelebrityEntity celebrityEntity);

    @Override
    CelebrityEntity findOneById(long id);

    CelebrityEntity findOneByCelebrityId(String celebrityId);
}
