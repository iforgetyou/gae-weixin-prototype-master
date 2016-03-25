package com.zy17.service.msghandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.zy17.service.WeixinMsgHandle;
import com.zy17.service.impl.ServiceUtil;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLImageMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 猜命令处理
 * Created by zy17 on 2016/3/11.
 */
@Slf4j
public class HelpCmdMsgHandle implements WeixinMsgHandle {
    public static final String HELP = ""
            + "  猜名人 -> 回复\"1\"\n"
            + "  想知道答案 -> 回复\"2\"\"\n"
            + "  清除历史成绩 -> 回复\"3\"\"\n"
            + "  上传名人 -> 上传图片后 再添加标签\n"
            + "  搜索名人 -> 回复姓名即可\n";
    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public boolean canHandle(EventMessage msg) {
        if (msg.getMsgType().equals(EventMessage.TEXT)) {
            String content = msg.getContent();
            if (content.equals("?")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String handleMsg(EventMessage msg) {
        // 回复帮助
        XMLTextMessage result = new XMLTextMessage(msg.getFromUserName(), msg.getToUserName(), HELP);
        return result.toXML();
    }
}