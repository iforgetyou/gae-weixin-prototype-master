package com.zy17.douban.api.movie;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.zy17.douban.api.BaseAPI;
import com.zy17.douban.bean.MovieSearchResult;
import com.zy17.service.googleservice.GaeHttpClient;

/**
 * 电影API
 * Created by zy17 on 2016/3/17.
 */
public class MovieApi extends BaseAPI {
    public static final String SEARCH_URL = "/v2/movie/search";

    /**
     * 搜索电影api
     *
     * @param query string	str	Y	Y	Y	-
     * @param tag   query string	str	Y	Y	Y	-
     * @param start int	Y	Y	Y	0
     * @param count int	Y	Y	Y	20
     *
     * @return
     */
    public static MovieSearchResult searchMovie(String query, String tag, int start, int count) {
        if (count < 1) {
            count = BaseAPI.DEFAULT_COUNT;
        }
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                //                .addHeader(jsonHeader)
                .setUri(BASE_URI + SEARCH_URL)
                .addParameter("q", query)
                .addParameter("tag", tag)
                .addParameter("start", String.valueOf(start))
                .addParameter("count", String.valueOf(count))
                .build();

        //        return LocalHttpClient.executeJsonResult(httpUriRequest, MovieSearchResult.class);
        return GaeHttpClient.executeJsonResult(httpUriRequest, MovieSearchResult.class);
    }
}
