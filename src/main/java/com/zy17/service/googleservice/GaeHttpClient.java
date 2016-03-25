package com.zy17.service.googleservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;

import com.zy17.weixin.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zy17 on 2016/3/17.
 */
@Slf4j
public class GaeHttpClient {
    public static <T> T executeJsonResult(HttpUriRequest request, final Class<T> clazz) {
        InputStream inputStream = null;
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        try {
            URL url = request.getURI().toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(request.getMethod());

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.close();
            log.debug("http request:{}", url);
            if (connection.getResponseCode() >= HttpURLConnection.HTTP_OK && connection.getResponseCode() < 300) {
                // OK
                StringBuffer resultBuffer = new StringBuffer();
                String tempLine;

                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);

                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                }
                log.debug("http response:{}", resultBuffer.toString());
                return JsonUtil.parseObject(new String(resultBuffer.toString().getBytes("iso-8859-1"), "utf-8"), clazz);

            } else {
                // Server returned HTTP error code.
                throw new ClientProtocolException("Unexpected response status: " + connection.getResponseCode());
            }
        } catch (Exception e) {
            log.error("http request error:" + request.getURI(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }

        }
        return null;
    }

}
