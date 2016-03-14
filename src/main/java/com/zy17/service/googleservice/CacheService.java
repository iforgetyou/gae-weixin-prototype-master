package com.zy17.service.googleservice;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 缓存工具
 * Created by zhangyan53 on 2016/3/11.
 */
@Slf4j
@Component
public class CacheService {
    private Cache cache;

    public CacheService() {
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
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
