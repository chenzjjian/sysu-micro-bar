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
 * Created by Administrator on 2016/6/7 0007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class HistoryMessageMapperTest {

    private static final Logger logger = LoggerFactory
            .getLogger(PostMapperTest.class);

    @Resource
    private HistoryMessageMapper historyMessageMapper;
    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void selectCountByNotChecked() throws Exception {
        logger.info(JSON.toJSONString(historyMessageMapper.selectCountByNotChecked(1)));
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

    @Test
    public void selectAllData() throws Exception {
        logger.info(JSON.toJSONString(historyMessageMapper.selectAllData(1, 0)));
        logger.info(JSON.toJSONString(historyMessageMapper.selectAllData(1, 0).size()));
    }

}