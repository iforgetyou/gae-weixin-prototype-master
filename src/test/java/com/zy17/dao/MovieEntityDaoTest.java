package com.zy17.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.datastore.Key;
import com.zy17.GaeBaseTest;
import com.zy17.douban.api.movie.MovieApi;
import com.zy17.douban.bean.MovieSearchResult;
import com.zy17.douban.bean.SimpleSubject;
import com.zy17.douban.convert.MovieEntityConvert;
import com.zy17.entity.BaseEntity;
import com.zy17.entity.MovieSearchResultEntity;

/**
 * Created by zhangyan53 on 2016/3/17.
 */
public class MovieEntityDaoTest extends GaeBaseTest {
    @Autowired
    MovieEntityDao dao;

    @Test
    public void testSave() throws Exception {
        MovieSearchResult result = MovieApi.searchMovie("胡歌", "", 0, 3);
        System.out.println(result);
        MovieSearchResultEntity entity = new MovieSearchResultEntity();
        for (SimpleSubject simpleSubject : result.getSubjects()) {
            MovieSearchResultEntity convert = MovieEntityConvert.convert(simpleSubject);
            convert.setImageItemId(5629499534213120l);
            Key key = dao.save(convert);
            assertNotNull(key);
        }
        List<MovieSearchResultEntity> byImageItemId = dao.findByImageItemId(5629499534213120l);
        System.out.println(byImageItemId);
        //        MovieSearchResultEntity byImageItemId = dao.findByImageItemId(5629499534213120l);
        //        System.out.println(byImageItemId);
        //        assertEquals(byImageItemId.getMovieSearchResult().getSubjects().size(), result.getSubjects().size
        // ());
    }

    @Test
    public void clazzTypeTest() {
        List<BaseEntity> embeddedList = new ArrayList<>();

        System.out.println(embeddedList.getClass());
        System.out.println(List.class.isAssignableFrom(embeddedList.getClass()));
    }
}