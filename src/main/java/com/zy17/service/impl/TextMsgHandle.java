package com.zy17.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.ScoredDocument;
import com.zy17.dao.ImageItemDao;
import com.zy17.model.ImageItem;
import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.googleservice.CacheService;
import com.zy17.service.googleservice.DocIndexService;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLImageMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 文本消息处理
 * Created by zhangyan53 on 2016/3/11.
 */
@Slf4j
@Component(value = "text")
public class TextMsgHandle implements WeixinMsgHandle {
    @Autowired
    ImageItemDao imageItemDao;
    @Autowired
    CacheService cacheUtil;
    @Autowired
    DocIndexService docIndexService;

    @Override
    public boolean canHandle(EventMessage msg) {
        return true;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        XMLMessage result = null;

        // 添加标签
        if (cacheUtil.getCache().get(msg.getFromUserName()) != null) {
            ImageItem imageItem = (ImageItem) cacheUtil.getCache().get(msg.getFromUserName());
            // 设置标签
            imageItem.setTextTag(msg.getContent());
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
            // 通过全文检索找标签
            ScoredDocument searchResult = docIndexService.search(DocIndexService.TAGS_DOC, "tag:"+msg.getContent());
            log.debug("search result:{}", searchResult);

            if (searchResult != null && StringUtils.isNotBlank(searchResult.getId())) {
                // 查找图片
                ImageItem imageItem = imageItemDao.findOneById(searchResult.getId());
                if (imageItem != null) {
                    // 回复图片
                    result = new XMLImageMessage(msg.getFromUserName(), msg.getToUserName(), imageItem.getMediaId());
                }
            }
        }
        // 回复文字
        if (result == null) {
            result = new XMLTextMessage(msg.getFromUserName(), msg.getToUserName(), "没有图,来一张吧! ");
        }

        return result.toXML();
    }
}
