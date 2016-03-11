package com.zy17.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zy17.dao.ImageItemDao;
import com.zy17.model.ImageItem;
import com.zy17.service.WeixinMsgHandle;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLImageMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

/**
 * 文本消息处理
 * Created by zhangyan53 on 2016/3/11.
 */
@Component(value = "text")
public class TextMsgHandle implements WeixinMsgHandle {
    @Autowired
    ImageItemDao imageItemDao;

    @Override
    public boolean canHandle(EventMessage msg) {
        return true;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        XMLMessage result = null;
        List<ImageItem> oneByTag = imageItemDao.findOneByTag(msg.getContent());
        if (oneByTag.size() != 0) {
            // 回复图片
            result = new XMLImageMessage(msg.getFromUserName(), msg.getToUserName(), oneByTag.get(0).getMediaId());
        } else {
            // 回复文字
            result = new XMLTextMessage(msg.getFromUserName(), msg.getToUserName(), "收到文本消息:" + msg.getContent());
        }

        return result.toXML();
    }
}
