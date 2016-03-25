package com.zy17.service.msghandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.impl.ServiceUtil;
import com.zy17.weixin.bean.message.EventMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 短视频处理
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class ShortvideoMsgHandle implements WeixinMsgHandle {
    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.SHORTVIDEO) || msg.getMsgType().equals(EventMessage.VIDEO)) {
            return true;
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        // 小视频, 转换成图片处理
        msg.setMsgType(EventMessage.IMAGE);
        return "";
    }
}