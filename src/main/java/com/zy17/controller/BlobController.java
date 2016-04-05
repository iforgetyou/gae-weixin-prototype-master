package com.zy17.controller;

import java.io.IOException;
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
 * 上传图片
 */
@Controller
@RequestMapping("/blob")
@Slf4j
public class BlobController {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @RequestMapping(method = RequestMethod.POST)
    public void upload(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("myFile");

        if (blobKeys == null || blobKeys.isEmpty()) {
            res.sendRedirect("/");
        } else {
            res.sendRedirect("/serve?blob-key=" + blobKeys.get(0).getKeyString());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public void blobserve(HttpServletResponse res, @RequestParam(value = "blob-key") String blobKeyParam) throws
            IOException {
        log.info("serve blob key:{}", blobKeyParam);
        BlobKey blobKey = new BlobKey(blobKeyParam);
        blobstoreService.serve(blobKey, res);
    }

}