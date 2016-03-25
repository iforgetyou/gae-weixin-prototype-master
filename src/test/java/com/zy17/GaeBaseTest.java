package com.zy17;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import lombok.extern.slf4j.Slf4j;

//import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
//import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * Created by zhangyan53 on 2016/3/17.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class GaeBaseTest {
    private final static LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    public String signature = "2a76f8e25cac924237b2b1ee8681269a2d3bdb73";
    public String timestamp = "1457329726";
    public String nonce = "849952710";
    public String echostr = "3257885424510564030";

    @BeforeClass
    public static void init() {
        helper.setUp();
        log.debug("help init:");
    }

    @AfterClass
    public static void destroy() {
        helper.tearDown();
        log.debug("help destroy:");
    }
}
