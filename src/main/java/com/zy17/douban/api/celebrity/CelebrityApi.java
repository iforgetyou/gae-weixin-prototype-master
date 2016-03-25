package com.zy17.douban.api.celebrity;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.zy17.douban.api.BaseAPI;
import com.zy17.douban.bean.CelebrityResult;
import com.zy17.douban.bean.MovieSearchResult;
import com.zy17.service.googleservice.GaeHttpClient;

/**
 * 影人条目API
 * Created by zy17 on 2016/3/17.
 */
public class CelebrityApi extends BaseAPI {
    public static final String CELEBRITY_URL = "/v2/movie/celebrity/";

    /**
     * 影人条目信息
     *
     * @param celebrityId 影人id
     *
     * @return
     */
    public static CelebrityResult findCelebrity(String celebrityId) {

        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + CELEBRITY_URL + celebrityId)
                .build();

        return GaeHttpClient.executeJsonResult(httpUriRequest, CelebrityResult.class);
    }
}
