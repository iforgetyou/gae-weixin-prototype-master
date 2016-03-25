/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.zy17.weixin.support;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.zy17.weixin.bean.token.Token;
import com.zy17.weixin.api.TokenAPI;

import lombok.extern.slf4j.Slf4j;

/**
 * TokenManager token 自动刷新
 *
 * @author LiYi
 */
@Slf4j
public class TokenManager {

    private static ScheduledExecutorService scheduledExecutorService;

    public static Map<String, String> tokenMap = new LinkedHashMap<String, String>();

    private static Map<String, ScheduledFuture<?>> futureMap = new HashMap<String, ScheduledFuture<?>>();

    private static int poolSize = 2;

    private static boolean daemon = Boolean.TRUE;

    /**
     * 初始化 scheduledExecutorService
     */
    private static void initScheduledExecutorService() {
        scheduledExecutorService = Executors.newScheduledThreadPool(poolSize, new ThreadFactory() {

            @Override
            public Thread newThread(Runnable arg0) {
                Thread thread = Executors.defaultThreadFactory().newThread(arg0);
                //设置守护线程
                thread.setDaemon(daemon);
                return thread;
            }
        });
    }

    /**
     * 设置线程池
     *
     * @param poolSize
     */
    public static void setPoolSize(int poolSize) {
        TokenManager.poolSize = poolSize;
    }

    /**
     * 设置线程方式
     *
     * @param daemon
     */
    public static void setDaemon(boolean daemon) {
        TokenManager.daemon = daemon;
    }

    /**
     * 初始化token 刷新，每118分钟刷新一次。
     *
     * @param appid
     * @param secret
     */
    public static void init(final String appid, final String secret) {
        if (scheduledExecutorService == null) {
            initScheduledExecutorService();
        }
        if (futureMap.containsKey(appid)) {
            futureMap.get(appid).cancel(true);
        }
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Token token = TokenAPI.token(appid, secret);
                tokenMap.put(appid, token.getAccess_token());
            }
        }, 0, 118, TimeUnit.MINUTES);
        futureMap.put(appid, scheduledFuture);
    }

    /**
     * 取消 token 刷新
     */
    public static void destroyed() {
        scheduledExecutorService.shutdownNow();
    }

    /**
     * 获取 access_token
     *
     * @param appid
     *
     * @return
     */
    public static String getToken(String appid) {
        return tokenMap.get(appid);
    }

    /**
     * 获取第一个appid 的 access_token
     * 适用于单一微信号
     *
     * @return
     */
    public static String getDefaultToken() {
        if (tokenMap.size() < 1) {
            TokenManager.refreshToken();
        }
        Object[] objs = tokenMap.values().toArray();
        return objs.length > 0 ? objs[0].toString() : null;
    }

    public static void refreshToken() {
        String appid = "wxd1634667029f343a";
        String secret = "01a0cab011cd7a937a2e1e24eb250fb1";
        Token token = TokenAPI.token(appid, secret);
        TokenManager.tokenMap.put(appid, token.getAccess_token());
        log.info("更换token成功{}", token.getAccess_token());
    }

}
