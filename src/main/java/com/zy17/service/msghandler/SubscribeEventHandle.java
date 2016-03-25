package com.zy17.service.msghandler;

import com.zy17.service.WeixinMsgHandle;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 图片消息处理
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class SubscribeEventHandle implements WeixinMsgHandle {

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.EVENT) && msg.getEvent().equals(EventMessage.SUBSCRIBE)) {
            return true;
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        // 关注后,显示帮助
        XMLMessage xmlTextMessage = new XMLTextMessage(
                msg.getFromUserName(),
                msg.getToUserName(),
                HelpCmdMsgHandle.HELP);
        return xmlTextMessage.toXML();
    }
}
