package com.zy17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zy17.service.googleservice.PushQueueService;
import com.zy17.service.msghandler.HelpCmdMsgHandle;
import com.zy17.weixin.api.MessageAPI;
import com.zy17.weixin.bean.message.massmessage.MassMessage;
import com.zy17.weixin.bean.message.massmessage.MassTextMessage;
import com.zy17.weixin.support.TokenManager;

import lombok.extern.slf4j.Slf4j;

/**
 * 执行任务
 */
@Slf4j
@Controller
@RequestMapping("/queue")
public class PushQueueController {
    @Autowired
    PushQueueService service;

    @RequestMapping(value = "/worker", method = RequestMethod.POST)
    public void test() {
        log.debug("push queue work");
    }

}