package com.zy17.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zy17.GaeBaseTest;
import com.zy17.douban.api.movie.RankApi;
import com.zy17.douban.bean.CelebrityResult;
import com.zy17.douban.bean.MovieTop250Result;
import com.zy17.douban.bean.SimpleSubject;
import com.zy17.douban.convert.CelebrityEntityConvert;
import com.zy17.douban.convert.MovieEntityConvert;
import com.zy17.entity.CelebrityEntity;
import com.zy17.entity.MovieSearchResultEntity;
import com.zy17.weixin.client.LocalHttpClient;

/**
 * Created by zhangyan53 on 2016/3/20.
 */
public class JobsControllerTest extends GaeBaseTest {

    @Autowired
    JobsController controller;

    @Test
    public void testDownloadTop250() throws Exception {
        Map<String, Object> hisCelebrity = new HashMap<>();
        int start = 0;
        int count = 250;
        int pagesize = 30;
        for (int i = start; i < count; ) {
            MovieTop250Result movieTop250Result = RankApi.top250Rank(i, pagesize);
            i += pagesize;
            for (SimpleSubject simpleSubject : movieTop250Result.getSubjects()) {
                MovieSearchResultEntity movie = MovieEntityConvert.convert(simpleSubject);
                downloadImg(movie.getImageLarge(), movie.getTitle());
                // 电影入库
                List<CelebrityResult> casts = simpleSubject.getCasts();
                for (CelebrityResult cast : casts) {
                    CelebrityEntity celebrity = CelebrityEntityConvert.convert(cast);
                    if (!hisCelebrity.containsKey(cast.getId())) {
                        System.out.println(celebrity);
                        if (celebrity.getAvatarLarge() == null) {
                            continue;
                        }
                        //                        downloadImg(celebrity.getAvatarLarge(),celebrity.getName().replace
                        // ("·",""));
                        hisCelebrity.put(cast.getId(), celebrity);
                    } else {
                        System.out.println("put already" + cast.getId());
                    }
                }

            }
            // 休眠1秒
            Thread.sleep(1000);
        }
    }

    private void downloadImg(String imageUrl, String fileName) throws IOException {

        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(imageUrl.replace("https", "http"))
                .build();
        CloseableHttpResponse response = LocalHttpClient.execute(httpUriRequest);

        try {
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() >= 400) {
                throw new IOException(
                        "Got bad response, error code = " + response.getStatusLine().getStatusCode()
                                + " imageUrl: " + imageUrl);
            }
            if (entity != null) {
                InputStream input = entity.getContent();
                OutputStream output = new FileOutputStream(new File("D:\\tmp\\celebrity\\" + fileName + ".gif"));
                IOUtils.copy(input, output);
                output.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
        }

    }
}