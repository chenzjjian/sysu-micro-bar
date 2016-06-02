package com.hyj.dao;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class FloorMapperTest {



    private static final Logger logger = LoggerFactory
            .getLogger(FloorMapperTest.class);

    @Resource
    private FloorMapper floorMapper;

    @Test
    public void selectByPrimaryKey() throws Exception {
//        logger.info(JSON.toJSONString(floorMapper.selectByPrimaryKey(1)));
        logger.info(JSON.toJSONString(floorMapper.selectByPrimaryKey(1).getReplyFloor()));

    }

    @Test
    public void selectCountByPostId() throws Exception {
        logger.info(JSON.toJSONString(floorMapper.selectCountByPostId(1)));
    }
    @Test
    public void selectByPostId() throws Exception {
        logger.info(JSON.toJSONString(floorMapper.selectByPostId(1)));
    }
    @Test
    public void selectByReply() throws Exception {
        logger.info(JSON.toJSONString(floorMapper.selectByReply(1)));
    }
}