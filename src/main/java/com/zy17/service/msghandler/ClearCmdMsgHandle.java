package com.zy17.service.msghandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.zy17.entity.UserStatistics;
import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.googleservice.CacheService;
import com.zy17.service.impl.ServiceUtil;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 清理命令处理 清楚缓存上下文,重新再猜
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class ClearCmdMsgHandle implements WeixinMsgHandle {
    @Autowired
    ServiceUtil serviceUtil;
    @Autowired
    CacheService cacheService;

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.TEXT)) {
            String content = msg.getContent();
            if (content.equals("3") || content.equals("重来")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        // 回复随机图片
        UserStatistics statistics = serviceUtil.getStatistics(msg.getFromUserName());
        serviceUtil.clearStatistics(msg.getFromUserName());
        cacheService.getCache().remove(msg.getFromUserName());
        String correctMsg = "(⑉°з°)-♡ "
                + "认出了 " + statistics.getCorrectCount().size() + " 位名人";
        XMLTextMessage result = new XMLTextMessage(msg.getFromUserName(), msg.getToUserName(), correctMsg +
                "\n只不过是重头再来");
        return result.toXML();
    }
}