package com.hyj.dao;

import com.alibaba.fastjson.JSON;
import com.hyj.entity.Post;
import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class PostMapperTest {
    @Test
    public void selectPostByCreator() throws Exception {
        List<Post> posts = postMapper.selectPostByCreator(13331095);
        logger.info(JSON.toJSONString(posts));
    }


    private static final Logger logger = LoggerFactory
            .getLogger(PostMapperTest.class);

    @Resource
    private PostMapper postMapper;
    @Test
    public void insertSelective() throws Exception {
        Post post = new Post();
    }

    @Test
    public void selectAllPost() throws Exception {
        logger.info(JSON.toJSONString(postMapper.selectAllPost(0)));
        for (Post post : postMapper.selectAllPost(0)) {
            logger.info(JSON.toJSONString(post.getCreator()));
        }
    }
    @Test
    public void searchByTitleAndTag() throws Exception {
        logger.info(JSON.toJSONString(postMapper.searchByTitleAndTag(null, null)));
        logger.info("" + postMapper.searchByTitleAndTag(null, null).size());
        logger.info("" + postMapper.searchByTitleAndTag(null, null).size());
    }
}