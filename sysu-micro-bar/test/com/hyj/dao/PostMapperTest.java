package com.hyj.dao;

import com.alibaba.fastjson.JSON;
import com.hyj.entity.Post;
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
public class PostMapperTest {


    private static final Logger logger = LoggerFactory
            .getLogger(PostMapperTest.class);

    @Resource
    private PostMapper postMapper;
    @Test
    public void insertSelective() throws Exception {
    }

    @Test
    public void selectAllPost() throws Exception {
        logger.info(JSON.toJSONString(postMapper.selectAllPost()));
        for (Post post : postMapper.selectAllPost()) {
            logger.info(JSON.toJSONString(post.getCreator()));
        }
    }
    @Test
    public void searchByTitleAndTag() throws Exception {
        logger.info(JSON.toJSONString(postMapper.searchByTitleAndTag(null, 1)));
    }
}