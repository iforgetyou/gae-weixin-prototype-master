package com.zy17.weixin.api;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zy17.weixin.bean.token.Token;

/**
 * Created by zhangyan53 on 2016/3/17.
 */
public class TokenAPITest {

    @Test
    public void testToken() throws Exception {
        String appid = "wx54333658f13b29b3";
        String secret = "af89386896df91486286c5c9c0c284a0";
        Token token = TokenAPI.token(appid, secret);
        System.out.println(JSON.toJSONString(token));
    }
}