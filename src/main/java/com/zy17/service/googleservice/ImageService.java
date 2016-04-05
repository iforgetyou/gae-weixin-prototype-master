package com.zy17.service.googleservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhangyan53 on 2016/3/30.
 */
@Slf4j
@Component
public class ImageService {

    public byte[] getImageFromUrl(String picurl) {
        byte[] bytes = new byte[0];
        try {
            URL url = new URL("picurl");
            bytes = input2byte(url.openStream());
        } catch (Exception e) {
            log.error("get image failed", e);
        }
        return bytes;
    }

    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
