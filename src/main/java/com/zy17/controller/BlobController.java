package com.zy17.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.zy17.dao.CelebrityDao;
import com.zy17.dao.ImageItemDao;
import com.zy17.dao.MovieEntityDao;
import com.zy17.douban.api.movie.RankApi;
import com.zy17.douban.bean.CelebrityResult;
import com.zy17.douban.bean.MovieTop250Result;
import com.zy17.douban.bean.SimpleSubject;
import com.zy17.douban.convert.CelebrityEntityConvert;
import com.zy17.douban.convert.MovieEntityConvert;
import com.zy17.entity.CelebrityEntity;
import com.zy17.entity.ImageItem;
import com.zy17.entity.MovieSearchResultEntity;
import com.zy17.service.googleservice.CacheService;
import com.zy17.service.googleservice.DocIndexService;
import com.zy17.service.msghandler.HelpCmdMsgHandle;
import com.zy17.weixin.api.MessageAPI;
import com.zy17.weixin.bean.message.massmessage.MassMessage;
import com.zy17.weixin.bean.message.massmessage.MassTextMessage;
import com.zy17.weixin.support.TokenManager;

import lombok.extern.slf4j.Slf4j;

/**
 * gae上传图片后回调
 */
@Controller
@RequestMapping("/images")
@Slf4j
public class BlobController {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    private ImagesService imagesService = ImagesServiceFactory.getImagesService();

    /**
     * 从jsp中取图片文件,转存到
     *
     * @param req
     * @param res
     *
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void upload(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("myFile");

        if (blobKeys == null || blobKeys.isEmpty()) {
            res.sendRedirect("/");
        } else {
            BlobKey blobKey = blobKeys.get(0);
            log.info("serve blob key:{}", blobKey);
            // 存blob
            blobstoreService.serve(blobKey, res);
            // 更新blob-key字段
            String imageUrl = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKey));
            // 通过用图片访问url,不受权限控制
            log.info("serve blob imageUrl:{}", imageUrl);
            // 更新gae image url字段
        }
    }

    /**
     * 直接通过blobkey获取图片,私密性比较好
     *
     * @param response
     * @param blobKeyParam
     *
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    public void getImages(HttpServletResponse response, @RequestParam(value = "blob-key") String blobKeyParam) throws
            IOException {
        BlobKey blobKey = new BlobKey(blobKeyParam);
        Image image = ImagesServiceFactory.makeImageFromBlob(blobKey);
        byte[] data = image.getImageData();
        response.setContentType("image/jpeg");

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }

}