package com.zy17.douban.api.movie;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zy17.GaeBaseTest;
import com.zy17.douban.bean.MovieTop250Result;
import com.zy17.douban.bean.UsBoxResult;

/**
 * Created by zhangyan53 on 2016/3/18.
 */
public class RankApiTest {

    @Test
    public void testUsboxRank() throws Exception {
        UsBoxResult usBoxResult = RankApi.usboxRank();
        System.out.println(usBoxResult);
    }

    @Test
    public void testTop250Rank() throws Exception {
        MovieTop250Result movieTop250Result = RankApi.top250Rank(0, 10);
        System.out.println(movieTop250Result);
    }
}