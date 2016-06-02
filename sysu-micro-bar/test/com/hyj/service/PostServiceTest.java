package com.hyj.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class PostServiceTest {


    private static Logger logger = Logger.getLogger(PostServiceTest.class);
    @Resource
    private PostService postService = null;
    @Test
    public void getPostDataList() throws Exception {
        logger.info(JSON.toJSONString(postService.getPostDataList()));
    }

    @Test
    public void getAllFloorDatas() throws Exception {
        logger.info(JSON.toJSONString(postService.getAllFloorDatas(1)));
    }

}