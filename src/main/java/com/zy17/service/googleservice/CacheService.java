package com.zy17.service.googleservice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import org.springframework.stereotype.Component;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 缓存工具
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
@Component
public class CacheService {
    public static final String IMAGE_ENTITY = "image";
    public static final String MOVIE_ENTITY = "movie";

    private Cache cache;

    public CacheService() {
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            Map properties = new HashMap<>();
            // 设置超期时间
            properties.put(GCacheFactory.EXPIRATION_DELTA, TimeUnit.HOURS.toSeconds(2));
            cache = cacheFactory.createCache(properties);
        } catch (CacheException e) {
            log.error("cache init error", e);
        }

    }

    public Cache getCache() {
        return cache;
    }

    public void cache(Object key, Object value) {
        cache.put(key, value);
    }

}
