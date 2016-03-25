package com.zy17.douban.api.movie;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.zy17.douban.api.BaseAPI;
import com.zy17.douban.bean.MovieSearchResult;
import com.zy17.douban.bean.MovieTop250Result;
import com.zy17.douban.bean.UsBoxResult;
import com.zy17.service.googleservice.GaeHttpClient;

/**
 * 榜单api
 * Created by zy17 on 2016/3/17.
 */
public class RankApi extends BaseAPI {
    // 北美票房
    public static final String US_BOX_URL = "/v2/movie/us_box";
    // Top250
    public static final String TOP_250_URL = "/v2/movie/top250";

    /**
     * 北美票房榜
     *
     * @return
     */
    public static UsBoxResult usboxRank() {
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + US_BOX_URL)
                .build();

        return GaeHttpClient.executeJsonResult(httpUriRequest, UsBoxResult.class);
    }

    /**
     * Top250
     *
     * @return
     */
    public static MovieTop250Result top250Rank(int start, int count) {
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + TOP_250_URL)
                .addParameter("start", String.valueOf(start))
                .addParameter("count", String.valueOf(count))
                .build();

        return GaeHttpClient.executeJsonResult(httpUriRequest, MovieTop250Result.class);
    }
}
