package com.hyj.service.impl;

import com.alibaba.fastjson.JSON;
import com.hyj.service.PostFloorService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class PostFloorServiceImplTest {
    @Test
    public void searchPostData() throws Exception {
        logger.info(JSON.toJSONString(postFloorService.searchPostData("咯", null)));
        logger.info(JSON.toJSONString(postFloorService.searchPostData("咯", null).size()));
    }


    private static Logger logger = Logger.getLogger(PostFloorServiceImplTest.class);
    @Resource
    private PostFloorService postFloorService = null;
    @Test
    public void getAllFloorDatas() throws Exception {
        logger.info(JSON.toJSONString(postFloorService.getAllFloorDatas(1)));
    }
    @Test
    public void getPostDataList() throws Exception {
        logger.info(JSON.toJSONString(postFloorService.getPostDataList(0)));
    }


}