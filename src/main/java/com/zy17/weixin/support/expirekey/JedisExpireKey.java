/*
 * Copyright (C) 2016 , Inc. All Rights Reserved.
 */
package com.zy17.weixin.support.expirekey;

import com.zy17.weixin.support.ExpireKey;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisExpireKey implements ExpireKey {

    private JedisPool pool;

    private static final String DEFAULT_VALUE = "";

    private String perfix = "WP_ExpireKey_";

    public JedisExpireKey() {
    }

    public JedisExpireKey(JedisPool pool) {
        this.pool = pool;
    }

    public void setPerfix(String perfix) {
        this.perfix = perfix;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    @Override
    public boolean add(String key, int expire) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(perfix + key, DEFAULT_VALUE);
            jedis.expire(perfix + key, expire);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean add(String key) {
        return add(key, DEFAULT_EXPIRE);
    }

    public boolean exists(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.exists(perfix + key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
