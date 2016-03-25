package com.zy17.douban.api.celebrity;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.zy17.douban.api.movie.MovieApi;
import com.zy17.douban.api.movie.RankApi;
import com.zy17.douban.bean.CelebrityResult;
import com.zy17.douban.bean.MovieSearchResult;
import com.zy17.douban.bean.MovieTop250Result;
import com.zy17.douban.bean.SimpleSubject;
import com.zy17.douban.convert.CelebrityEntityConvert;
import com.zy17.entity.CelebrityEntity;

/**
 * Created by zhangyan53 on 2016/3/19.
 */
public class CelebrityApiTest {

    @Test
    public void testFindCelebrity() throws Exception {
        CelebrityResult celebrity = CelebrityApi.findCelebrity("1041404");
        System.out.println(celebrity);
    }

}