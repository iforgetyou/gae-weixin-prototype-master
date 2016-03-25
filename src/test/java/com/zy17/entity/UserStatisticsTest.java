package com.zy17.entity;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by zhangyan53 on 2016/3/20.
 */
public class UserStatisticsTest {

    @Test
    public void testAddGuessHis() throws Exception {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.addGuessHis(123l, true);
        userStatistics.addGuessHis(123l, false);
        System.out.println(userStatistics.countWrong(123l));
    }
}