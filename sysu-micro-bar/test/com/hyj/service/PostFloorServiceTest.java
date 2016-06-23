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
public class PostFloorServiceTest {
    private static Logger logger = Logger.getLogger(PostFloorServiceTest.class);
    @Resource
    private PostFloorService postFloorService = null;

    @Test
    public void createReply() throws Exception {
        postFloorService.createReply(2, 24, 39, "66666你这个僚机", null, null, null);
    }

    @Test
    public void createFloor() throws Exception {
        postFloorService.createFloor(2, 24, "你行你上", null, null, null);
    }


    @Test
        public void createPost() throws Exception {
//        postFloorService.createPost(1, "测试1", 1, "好好干", null, null, null);
            postFloorService.createPost(13331095, "测试啊发发飒飒3", 1, "6啊发发飒啊发发飒啊发发飒啊发发飒啊发发飒啊发发飒啊发发飒66", null, null, null);
//        postFloorService.createPost(3, "测试3", 1, "666", null, null, null);
    }
    @Test
    public void getPostDataList() throws Exception {
        logger.info(JSON.toJSONString(postFloorService.getPostDataList(0)));
    }

    @Test
    public void getAllFloorDatas() throws Exception {
        logger.info(JSON.toJSONString(postFloorService.getAllFloorDatas(1)));
    }

}