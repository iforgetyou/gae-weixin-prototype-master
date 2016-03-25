package com.zy17.service.msghandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.impl.ServiceUtil;
import com.zy17.weixin.bean.message.EventMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 举报命令处理 todo 举报后删除上下文中的图片
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class ReportCmdMsgHandle implements WeixinMsgHandle {
    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.TEXT)) {
            String content = msg.getContent();
            if (content.equalsIgnoreCase("x")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {

        return "";
    }
}