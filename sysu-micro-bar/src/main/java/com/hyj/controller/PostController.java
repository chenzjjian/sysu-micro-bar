package com.hyj.controller;

import com.alibaba.fastjson.JSON;
import com.hyj.dto.FloorData;
import com.hyj.dto.PostData;
import com.hyj.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
@Controller
public class PostController {
    private static final Logger logger = LoggerFactory
            .getLogger(PostController.class);
    @Resource
    private PostService postService;

    @RequestMapping(value = "/getPostList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<PostData> getPostData(@RequestParam("currentPostNum") int currentPostNum) {
        logger.info(JSON.toJSONString(postService.getPostDataList(currentPostNum)));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("postData", result);
        result.put("hasMessage", false);
        return postService.getPostDataList(currentPostNum);
    }
    @RequestMapping(value = "/searchPostList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<PostData> searchPostList(@RequestParam("title") String title, @RequestParam("tag") int tag) {
        logger.info(JSON.toJSONString(postService.searchPostData(title, tag)));
        return postService.searchPostData(title, tag);
    }

    @RequestMapping(value = "/seePostDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<FloorData> seePost(@RequestParam("postId") int postId) {
        logger.info(JSON.toJSONString(postService.getAllFloorDatas(postId)));
        return postService.getAllFloorDatas(postId);
    }






/*    @RequestMapping(value = "/seeReply", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<ReplyData> seeReply(@RequestParam("accountId") int accountId) {
        return postService.getReplyDataByAccountId(accountId);
    }*/


}
