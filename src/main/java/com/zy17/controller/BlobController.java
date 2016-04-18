package com.zy17.controller;

import java.io.IOException;
import java.io.OutputStream;
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
import org.springframework.web.multipart.MultipartFile;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.zy17.dao.ImageItemDao;
import com.zy17.entity.ImageItem;

import lombok.extern.slf4j.Slf4j;

/**
 * gae上传图片后回调
 */
@Controller
@RequestMapping("/blob")
@Slf4j
public class BlobController {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    private ImagesService imagesService = ImagesServiceFactory.getImagesService();
    @Autowired
    ImageItemDao dao;

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
    public void upload(HttpServletRequest req, HttpServletResponse res,
                       @RequestParam(value = "tags", required = true) String tags,
                       @RequestParam(value = "type", required = true) String type,
                       @RequestParam(value = "files", required = false) MultipartFile[] files
    )
            throws ServletException, IOException {
        // 多图片上传
        for (MultipartFile file : files) {
            Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
            List<BlobKey> blobKeys = blobs.get(file.getName());

            if (blobKeys == null || blobKeys.isEmpty()) {
                res.sendRedirect("/");
                return;
            }
            for (BlobKey blobKey : blobKeys) {
                log.info("serve blob key:{}", blobKey);
                // 存blob
                blobstoreService.serve(blobKey, res);
                // 更新blob-key字段
                String imageUrl = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKey));
                // 通过用图片访问url,不受权限控制
                log.info("serve blob imageUrl:{}", imageUrl);
                // 更新gae image url字段
                ImageItem imageEntity = new ImageItem();
                imageEntity.setCreator("admin");
                imageEntity.setPicUrl(imageUrl);
                imageEntity.setTags(tags);
                imageEntity.setType(type);
                imageEntity.setBlobKey(blobKey.getKeyString());
                dao.save(imageEntity);

                // todo 请求豆瓣接口 保存影人信息 后续了解更多时用
            }
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