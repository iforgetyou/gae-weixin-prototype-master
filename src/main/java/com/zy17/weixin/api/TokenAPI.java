/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.zy17.weixin.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.zy17.weixin.bean.token.Token;
import com.zy17.weixin.client.LocalHttpClient;

public class TokenAPI extends BaseAPI {

    /**
     * 获取access_token
     *
     * @param appid
     * @param secret
     *
     * @return
     */
    public static Token token(String appid, String secret) {
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URI + "/cgi-bin/token")
                .addParameter("grant_type", "client_credential")
                .addParameter("appid", appid)
                .addParameter("secret", secret)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Token.class);
    }

}
