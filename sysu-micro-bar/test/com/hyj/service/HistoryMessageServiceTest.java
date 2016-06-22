package com.hyj.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class HistoryMessageServiceTest {

    @Resource
    private HistoryMessageService historyMessageService;
    private static final Logger logger = LoggerFactory.getLogger(HistoryMessageServiceTest.class);
    @Test
    public void loadMessage() throws Exception {
        logger.info(JSON.toJSONString(historyMessageService.loadMessage(1, 0)));
    }

}