package com.zy17.service.msghandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.zy17.dao.ImageItemDao;
import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.googleservice.CacheService;
import com.zy17.weixin.bean.message.EventMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 声音消息处理
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class VoiceMsgHandle implements WeixinMsgHandle {
    @Autowired
    ImageItemDao imageItemDao;
    @Autowired
    CacheService cacheUtil;

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.VOICE)) {
            return true;
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        // 接收到语音自动转出文本
        String recognition = msg.getRecognition();
        msg.setContent(recognition.substring(0, recognition.length() - 1));
        msg.setMsgType(EventMessage.TEXT);
        return "";
    }
}
