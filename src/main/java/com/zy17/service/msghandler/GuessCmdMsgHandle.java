package com.zy17.service.msghandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.impl.ServiceUtil;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLImageMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 猜命令处理
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class GuessCmdMsgHandle implements WeixinMsgHandle {
    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.TEXT)) {
            String content = msg.getContent();
            if (content.equals("1") || content.equals("猜") || content.equals("来一个")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        // 回复随机图片
        XMLImageMessage result = serviceUtil.convertImageToWeixinMsg(msg);
        if (result == null) {
            return "success";
        }
        return result.toXML();
    }
}