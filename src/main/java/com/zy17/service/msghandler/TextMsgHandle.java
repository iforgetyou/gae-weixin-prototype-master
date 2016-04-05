package com.zy17.service.msghandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.ScoredDocument;
import com.zy17.dao.ImageItemDao;
import com.zy17.dao.MovieEntityDao;
import com.zy17.douban.api.movie.MovieApi;
import com.zy17.douban.bean.MovieSearchResult;
import com.zy17.douban.bean.SimpleSubject;
import com.zy17.douban.convert.MovieEntityConvert;
import com.zy17.entity.ImageItem;
import com.zy17.entity.MovieSearchResultEntity;
import com.zy17.entity.UserStatistics;
import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.googleservice.CacheService;
import com.zy17.service.googleservice.DocIndexService;
import com.zy17.service.googleservice.PushQueueService;
import com.zy17.service.impl.ServiceUtil;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLImageMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLNewsMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 文本消息处理
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class TextMsgHandle implements WeixinMsgHandle {
    @Autowired
    ImageItemDao imageItemDao;
    @Autowired
    MovieEntityDao movieEntityDao;
    @Autowired
    CacheService cacheUtil;
    @Autowired
    DocIndexService docIndexService;
    @Autowired
    PushQueueService queueService;
    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.TEXT)) {
            return true;
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        XMLMessage result = null;
        String content = msg.getContent();

        // 是否有上下文
        if (cacheUtil.getCache().get(msg.getFromUserName()) != null) { // 有上下文
            // 判断上下文类型
            ImageItem imageItem = (ImageItem) cacheUtil.getCache().get(msg.getFromUserName());
            log.debug("get cache{}", imageItem);
            if (imageItem.getTags() == null || imageItem.getTags().isEmpty()) {
                // 缓存已上传图片,添加标签
                // 设置标签
                imageItem.setTags(msg.getContent());
                // 入库保存
                Key key = imageItemDao.save(imageItem);
                if (key != null) { // 入库成功
                    // 加入全文检索
                    Document doc = Document.newBuilder()
                            .setId(String.valueOf(key.getId()))
                            .addField(Field.newBuilder().setName("tag").setText(msg.getContent())).build();
                    docIndexService.indexADocument(DocIndexService.TAGS_DOC, doc);
                }
                // 移除缓存
                cacheUtil.getCache().remove(msg.getFromUserName());
                result = new XMLTextMessage(msg.getFromUserName(), msg.getToUserName(), "成功添加,搜下试试?");
            } else {
                if (content.equals("2")) {
                    // 求答案呢
                    result = new XMLTextMessage(msg.getFromUserName(), msg.getToUserName(),
                            "这位名人叫:" + imageItem.getTags());
                }
                // 缓存已经发图,匹配答案
                String[] tags = imageItem.getTags().split(" ");
                for (String tag : tags) {
                    if (tag.equals(content)) {
                        serviceUtil.statistics(msg.getFromUserName(), true, imageItem.getID());
                        result = serviceUtil.convertImageToWeixinMsg(msg);
                        if (result == null) {
                            // 答对了,没有图了,清空缓存
                            cacheUtil.getCache().remove(msg.getFromUserName());
                        }
                        break;
                    }
                }
                if (result == null) {
                    UserStatistics statistics =
                            serviceUtil.statistics(msg.getFromUserName(), false, imageItem.getID());
                    String correctMsg = "(⑉°з°)-♡ "
                            + "认出了 " + statistics.getCorrectCount().size() + " 位名人";

                    List<MovieSearchResultEntity> movies = movieEntityDao.findByImageItemId(imageItem.getID());
                    if (movies.size() == 0) {
                        int pagesize = 3;
                        MovieSearchResult movieSearchResult =
                                MovieApi.searchMovie(tags[0], "", statistics.countWrong(imageItem.getID()),
                                        pagesize);
                        for (SimpleSubject simpleSubject : movieSearchResult.getSubjects()) {
                            MovieSearchResultEntity movie = MovieEntityConvert.convert(simpleSubject);
                            movie.setImageItemId(imageItem.getID());
                            movieEntityDao.asyncSave(movie);
                            movies.add(movie);
                        }
                    }

                    ArrayList<XMLNewsMessage.Article> articles = new ArrayList<>();
                    for (MovieSearchResultEntity movieSearchResultEntity : movies) {
                        articles.add(
                                new XMLNewsMessage.Article(movieSearchResultEntity.getTitle(),
                                        movieSearchResultEntity.getOriginal_title(),
                                        movieSearchResultEntity.getAlt(),
                                        movieSearchResultEntity.getImageLarge()));
                    }
                    articles.add(new XMLNewsMessage.Article(correctMsg, correctMsg, "", ""));
                    result = new XMLNewsMessage(msg.getFromUserName(), msg.getToUserName(), articles);
                }
            }
        } else {
            // 找图片,通过全文检索
            ScoredDocument searchResult =
                    docIndexService.search(DocIndexService.TAGS_DOC, "tag:" + msg.getContent());
            log.debug("search result:{}", searchResult);

            if (searchResult != null && StringUtils.isNotBlank(searchResult.getId())) {
                // 查找图片
                ImageItem imageItem = imageItemDao.findOneById(Long.valueOf(searchResult.getId()));
                if (imageItem != null) {
                    // 回复图片
                    result =
                            new XMLImageMessage(msg.getFromUserName(), msg.getToUserName(), imageItem.getMediaId());
                }
            }
        }

        // 未找到回复文字
        if (result == null) {
            result = new XMLTextMessage(msg.getFromUserName(), msg.getToUserName(), HelpCmdMsgHandle.HELP);
        }
        log.debug("response:{}", result.toXML());
        return result.toXML();
    }

}
