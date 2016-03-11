package com.zy17.service.impl;

import org.springframework.stereotype.Component;

import com.zy17.service.WeixinMsgHandle;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

/**
 * 微信默认消息处理
 * Created by zhangyan53 on 2016/3/11.
 */
@Component(value = "default")
public class DefaultMsgHandle implements WeixinMsgHandle {
    @Override
    public boolean canHandle(EventMessage msg) {
        return true;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        // 创建回复
        XMLMessage xmlTextMessage = new XMLTextMessage(
                msg.getFromUserName(),
                msg.getToUserName(),
                "收到");
        //回复
        return xmlTextMessage.toXML();
    }
}
