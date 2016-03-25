package com.zy17.service;

import com.zy17.weixin.bean.message.EventMessage;

/**
 * Created by zy17 on 2016/3/11.
 */
public interface WeixinMsgHandle {
    /**
     * 是否能处理消息
     *
     * @param msg
     *
     * @return
     */
    boolean canHandle(EventMessage msg);

    /**
     * 消息处理逻辑
     *
     * @param msg
     *
     * @return
     */
    String handleMsg(EventMessage msg);
}
