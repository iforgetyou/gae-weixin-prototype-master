package com.zy17.douban.api.search;

import org.junit.Test;

import com.zy17.douban.api.movie.MovieApi;
import com.zy17.douban.bean.MovieSearchResult;

/**
 * Created by zhangyan53 on 2016/3/17.
 */
public class SearchApiTest {

    @Test
    public void testSearchMovie() throws Exception {
        MovieSearchResult result = MovieApi.searchMovie("胡歌", "", 0, 3);
        System.out.println(result);
    }
}