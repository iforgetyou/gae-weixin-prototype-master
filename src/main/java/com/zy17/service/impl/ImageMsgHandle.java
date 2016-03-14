package com.zy17.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zy17.dao.ImageItemDao;
import com.zy17.model.ImageItem;
import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.googleservice.CacheService;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 图片消息处理
 * Created by zhangyan53 on 2016/3/11.
 */
@Slf4j
@Component(value = "image")
public class ImageMsgHandle implements WeixinMsgHandle {
    @Autowired
    ImageItemDao imageItemDao;
    @Autowired
    CacheService cacheUtil;

    @Override
    public boolean canHandle(EventMessage msg) {
        return true;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        ImageItem imageItem = new ImageItem();
        imageItem.setOpenId(msg.getFromUserName());
        imageItem.setMediaId(msg.getMediaId());
        imageItem.setPicUrl(msg.getPicUrl());

        // 缓存userid,为了后续加tag用
        cacheUtil.cache(msg.getFromUserName(), imageItem);

        // 创建回复
        XMLMessage xmlTextMessage = new XMLTextMessage(
                msg.getFromUserName(),
                msg.getToUserName(),
                "收到图片消息,请回复文字,给图片添加标签");
        //回复
        return xmlTextMessage.toXML();
    }
}
