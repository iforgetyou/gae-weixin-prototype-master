package com.zy17.service.msghandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.zy17.dao.ImageItemDao;
import com.zy17.entity.ImageItem;
import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.googleservice.CacheService;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 图片消息处理
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class ImageMsgHandle implements WeixinMsgHandle {
    @Autowired
    ImageItemDao imageItemDao;
    @Autowired
    CacheService cacheUtil;

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.IMAGE)) {
            return true;
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        ImageItem imageItem = new ImageItem();
        imageItem.setCreator(msg.getFromUserName());
        imageItem.setMediaId(msg.getMediaId());
        imageItem.setPicUrl(msg.getPicUrl());

        // 缓存userid,为了后续加tag用
        cacheUtil.cache(msg.getFromUserName(), imageItem);

        // 创建回复
        XMLMessage xmlTextMessage = new XMLTextMessage(
                msg.getFromUserName(),
                msg.getToUserName(),
                "收到图片,请回复文字添加标签");

        //回复
        return xmlTextMessage.toXML();
    }
}
