package com.zy17.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.zy17.GaeBaseTest;
import com.zy17.weixin.bean.message.EventMessage;

/**
 * Created by zhangyan53 on 2016/3/18.
 */
public class WeixinControllerTest extends GaeBaseTest {
    @Autowired
    WeixinController controller;

    @Test
    public void testBusiness() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setMethod("POST");
        request.addParameter("username", "aa");
        request.addParameter("password", "bb");
        EventMessage eventMessage = new EventMessage();
        eventMessage.setMsgType(EventMessage.TEXT);
        eventMessage.setFromUserName("fromUser");
        eventMessage.setToUserName("toUser");
        eventMessage.setCreateTime(1348831860);
        eventMessage.setMsgId("msgid");
        eventMessage.setContent("1");
        String business = controller.business(signature, timestamp, nonce, eventMessage);
    }
}