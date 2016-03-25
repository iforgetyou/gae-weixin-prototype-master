package com.zy17.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.zy17.entity.MovieSearchResultEntity;

/**
 * Created by zy17 on 2016/3/17.
 */
public interface MovieEntityDao extends BaseDao<MovieSearchResultEntity> {
    List<MovieSearchResultEntity> findByImageItemId(long ImageItemID);

    void asyncSave(MovieSearchResultEntity movie);

    MovieSearchResultEntity findOneByMovieId(String movieId);
}
