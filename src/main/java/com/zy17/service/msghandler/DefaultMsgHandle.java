package com.zy17.service.msghandler;

import org.springframework.stereotype.Component;

import com.zy17.service.WeixinMsgHandle;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

/**
 * 微信默认消息处理
 * Created by zy17 on 2016/3/11.
 */
public class DefaultMsgHandle implements WeixinMsgHandle {
    public static final String SUCCESS="success";

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
                "功能还在完善,请持续关注^-^ ");
        //回复
        return xmlTextMessage.toXML();
    }
}
