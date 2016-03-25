package com.zy17.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import com.zy17.dao.MovieEntityDao;
import com.zy17.entity.MovieSearchResultEntity;

/**
 * Created by zy17 on 2016/3/17.
 */
@Repository
public class MovieEntityDaoImpl extends BaseDaoImpl<MovieSearchResultEntity>
        implements MovieEntityDao {
    @Override
    public void asyncSave(MovieSearchResultEntity entity) {
        super.asyncSave(entity);
    }

    @Override
    public MovieSearchResultEntity findOneByMovieId(String movieId) {
        Query.Filter imageItemIdFilter =
                new Query.FilterPredicate("movieId",
                        Query.FilterOperator.EQUAL,
                        movieId);
        PropertyProjection propertyProjection = new PropertyProjection("movieId", MovieSearchResultEntity.class);
        return super.findOne(imageItemIdFilter, propertyProjection);
    }

    @Override
    public MovieSearchResultEntity findOneById(long id) {
        MovieSearchResultEntity cacheMovie =
                (MovieSearchResultEntity) cacheService.getCache().get(cacheService.MOVIE_ENTITY + id);
        if (cacheMovie != null) {
            return cacheMovie;
        }
        return super.findById(id);
    }

    @Override
    public List<MovieSearchResultEntity> findByImageItemId(long ID) {
        Query.Filter imageItemIdFilter =
                new Query.FilterPredicate("imageItemId",
                        Query.FilterOperator.EQUAL,
                        ID);
        return super.find(imageItemIdFilter);
    }
}
