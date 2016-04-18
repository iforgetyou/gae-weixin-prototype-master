package com.zy17.controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
 * 执行任务
 */
@Controller
@RequestMapping("/jobs")
@Slf4j
public class JobsController {
    @Autowired
    MovieEntityDao movieEntityDao;
    @Autowired
    CelebrityDao celebrityDao;
    @Autowired
    ImageItemDao imageItemDao;
    @Autowired
    DocIndexService docIndexService;
    @Autowired
    CacheService cacheService;

    /**
     * 每日微信消息推送
     */
    @RequestMapping(value = "/everdaymsg", method = RequestMethod.GET)
    public void everydayMsg() {
        MassMessage message = new MassTextMessage(HelpCmdMsgHandle.HELP);
        MessageAPI.messageMassSendall(TokenManager.getDefaultToken(), message);
    }

    /**
     * 每日微信消息推送
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        log.debug("job executeJsonResult ");
    }

    /**
     * 每日微信消息推送
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public void tokenJob() {
        TokenManager.refreshToken();
    }

    /**
     * 重新建立全文检索
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void reConstructDocIndex() {
        // 删除所有索引
        docIndexService.deleteIndex(DocIndexService.TAGS_DOC);

        log.info("删除所有索引");
        // 重建库里所有数据
        List<Long> allId = imageItemDao.findAllValuableId();
        for (Long id : allId) {
            ImageItem imageItem = imageItemDao.findOneById(id);
            // 刷新缓存
            cacheService.getCache().put(CacheService.IMAGE_ENTITY + imageItem.getID(), imageItem);
            // 加入全文检索
            Document doc = Document.newBuilder()
                    .setId(String.valueOf(imageItem.getID()))
                    .addField(Field.newBuilder().setName("tag").setText(imageItem.getTags())).build();
            docIndexService.indexADocument(DocIndexService.TAGS_DOC, doc);
        }
        log.info("重建库里所有数据,size:{}", allId.size());
    }

    /**
     * 每日微信消息推送
     */
    @RequestMapping(value = "/top250", method = RequestMethod.GET)
    public void downloadTop250(@RequestParam(value = "st") int start,
                               @RequestParam(value = "ps") int pagesize,
                               @RequestParam(value = "ct") int count
    ) throws InterruptedException, URISyntaxException {
        Map<String, Object> hisCelebrity = new HashMap<>();
        for (int i = start; i < count; ) {
            MovieTop250Result movieTop250Result = RankApi.top250Rank(i, pagesize);
            i += pagesize;
            for (SimpleSubject simpleSubject : movieTop250Result.getSubjects()) {
                MovieSearchResultEntity movie = MovieEntityConvert.convert(simpleSubject);
                if (movieEntityDao.findOneByMovieId(movie.getMovieId()) == null) {
                    movieEntityDao.asyncSave(movie);
                }
                // 电影入库
                List<CelebrityResult> casts = simpleSubject.getCasts();
                for (CelebrityResult cast : casts) {
                    CelebrityEntity celebrity = CelebrityEntityConvert.convert(cast);
                    if (!hisCelebrity.containsKey(cast.getId())) {
                        log.debug("save ");
                        System.out.println(celebrity);
                        // 影人入库
                        if (celebrityDao.findOneByCelebrityId(celebrity.getCelebrityId()) == null) {
                            celebrityDao.save(celebrity);
                        }

                        //                        // 创建永久媒体id
                        //                        Media media = MediaAPI.mediaUpload(TokenManager.getDefaultToken(),
                        // MediaType.image,
                        //                                new URI(celebrity.getAvatarLarge() + "?"));
                        //                        // 保存imageItem
                        //                        ImageItem imageEntity = new ImageItem();
                        //                        imageEntity.setCreator("admin");
                        //                        imageEntity.setMediaId(media.getMedia_id());
                        //                        imageEntity.setType(imageEntity.CELEBRITY);
                        //                        imageEntity.addTag(celebrity.getName());
                        //                        imageItemDao.save(imageEntity);
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

}