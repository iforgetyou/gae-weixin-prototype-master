package com.zy17.service.msghandler;

import com.zy17.service.WeixinMsgHandle;
import com.zy17.weixin.bean.message.EventMessage;

/**
 * Created by zhangyan53 on 2016/3/29.
 */
public class ExcluedeEventHandle implements WeixinMsgHandle {
    @Override
    public boolean canHandle(EventMessage msg) {
        return true;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.TEXT)) {
            if (msg.getContent().equals("【收到不支持的消息类型，暂无法显示】")) {
                return DefaultMsgHandle.SUCCESS;
            }
        }
        return "";
    }
}
