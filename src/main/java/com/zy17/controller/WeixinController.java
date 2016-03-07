package com.zy17.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLMessage;
import com.zy17.weixin.bean.xmlmessage.XMLTextMessage;
import com.zy17.weixin.util.SignatureUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户操作
 */
@Controller
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    //从官方获取
    private String token = "omcUjifXTydnWzxUSwwtOClqa8luskvQ";

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    String echo(@RequestParam(value = "signature") String signature,
                @RequestParam(value = "timestamp") String timestamp,
                @RequestParam(value = "nonce") String nonce,
                @RequestParam(value = "echostr") String echostr) {

        //首次请求申请验证,返回echostr
        log.debug("get request" + echostr);
        //验证请求签名
        if (!signature.equals(SignatureUtil.generateEventMessageSignature(token, timestamp, nonce))) {
            log.warn("The request signature is invalid");
            return "The request signature is invalid";
        }

        if (echostr != null) {
            return echostr;
        }
        return "ok";
    }

    // 用户注册
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    String business(@RequestParam(value = "signature", required = false) String signature,
                    @RequestParam(value = "timestamp", required = false) String timestamp,
                    @RequestParam(value = "nonce", required = false) String nonce,
                    @RequestBody(required = false) EventMessage eventMessage) throws UnsupportedEncodingException {
        log.debug("post request:" + timestamp);
        //验证请求签名
        if (!signature.equals(SignatureUtil.generateEventMessageSignature(token, timestamp, nonce))) {
            log.warn("The request signature is invalid");
            //            return new GzipCompressingEntity(new StringEntity(""));
            return "signature check failed";
        }

        // 解析消息
        //        EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, eventMessage);
        log.debug("recieve msgId :{},msgType:{}", eventMessage.getMsgId(), eventMessage.getMsgType());
        // 消息排重
        String key = eventMessage.getFromUserName() + "_"
                + eventMessage.getToUserName() + "_"
                + eventMessage.getMsgId() + "_"
                + eventMessage.getCreateTime();
        // 业务处理
        // 创建回复
        XMLMessage xmlTextMessage = new XMLTextMessage(
                eventMessage.getFromUserName(),
                eventMessage.getToUserName(),
                "你好");
        //回复
        return xmlTextMessage.toXML();
    }

}