package com.zy17.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zy17.dao.ImageItemDao;
import com.zy17.dao.impl.ImageItemDaoImpl;
import com.zy17.model.ImageItem;
import com.zy17.service.WeixinMsgHandle;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

/**
 * 图片消息处理
 * Created by zhangyan53 on 2016/3/11.
 */
@Component(value = "image")
public class ImageMsgHandle implements WeixinMsgHandle {
    @Autowired
    ImageItemDao imageItemDao;

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
        imageItem.setTextTag("tag");

        imageItemDao.save(imageItem);
        // 创建回复
        XMLMessage xmlTextMessage = new XMLTextMessage(
                msg.getFromUserName(),
                msg.getToUserName(),
                "收到图片消息,请回复文字,给图片添加标签");
        //回复
        return xmlTextMessage.toXML();
    }
}
