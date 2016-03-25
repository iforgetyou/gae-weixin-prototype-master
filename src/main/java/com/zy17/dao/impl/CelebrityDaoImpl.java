package com.zy17.dao.impl;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import com.zy17.dao.CelebrityDao;
import com.zy17.entity.CelebrityEntity;
import com.zy17.entity.MovieSearchResultEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhangyan53 on 2016/3/19.
 */
@Slf4j
@Repository
public class CelebrityDaoImpl extends BaseDaoImpl<CelebrityEntity> implements CelebrityDao {
    @Override
    public Key save(CelebrityEntity celebrityEntity) {
        return super.save(celebrityEntity);
    }

    @Override
    public CelebrityEntity findOneById(long id) {
        return super.findById(id);
    }

    @Override
    public CelebrityEntity findOneByCelebrityId(String celebrityId) {
        Query.Filter celebrityIdFilter =
                new Query.FilterPredicate("celebrityId",
                        Query.FilterOperator.EQUAL,
                        celebrityId);
        PropertyProjection propertyProjection = new PropertyProjection("celebrityId", CelebrityEntity.class);

        return findOne(celebrityIdFilter, propertyProjection);
    }
}
