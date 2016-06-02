package com.hyj.controller;

import com.alibaba.fastjson.JSON;
import com.hyj.constant.Constants;
import com.hyj.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public String getPostData() {
        logger.info(JSON.toJSONString(postService.getPostDataList()));
        return JSON.toJSONString(postService.getPostDataList());
    }
    @RequestMapping(value = "/searchPostList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String searchPostList(@RequestParam("title") String title, @RequestParam("tag") int tag) {
        logger.info(JSON.toJSONString(postService.searchPostData(title, tag)));
        return JSON.toJSONString(postService.searchPostData(title, tag));
    }

    @RequestMapping(value = "/seePostDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String seePost(@RequestParam("postId") int postId) {
        logger.info(JSON.toJSONString(postService.getAllFloorDatas(postId)));
        return JSON.toJSONString(postService.getAllFloorDatas(postId));
    }

    @RequestMapping(value = "/createPost", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String createPost(@RequestParam("accountId") int accountId,
                             @RequestParam("title") String title,
                             @RequestParam("detail") String detail,
                             @RequestParam("tag") int tag,
                             @RequestParam("file") MultipartFile[] files,
                             HttpServletRequest request) {
        logger.info("detail " + detail);
        logger.info("title " + title);
        logger.info("tag " + Constants.POST_TAGS[tag]);
        /*上传文件根路径*/
        String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload";
        /*对应url根路径*/
        String contextPath = request.getContextPath() + "/upload";
        return JSON.toJSONString(postService.createPost(accountId, title, tag, detail, files, rootPath, contextPath));
    }

    @RequestMapping(value = "/createFloor", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String createPost(@RequestParam("accountId") int accountId,
                             @RequestParam("postId") int postId,
                             @RequestParam("detail") String detail,
                             @RequestParam("file") MultipartFile[] files,
                             HttpServletRequest request) {
        logger.info("detail " + detail);
        /*上传文件根路径*/
        String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload";
        /*对应url根路径*/
        String contextPath = request.getContextPath() + "/upload";
        return JSON.toJSONString(postService.createFloor(accountId, postId, detail, files, rootPath, contextPath));
    }

    @RequestMapping(value = "/createReply", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String createPost(@RequestParam("accountId") int accountId,
                             @RequestParam("postId") int postId,
                             @RequestParam("replyFloorId") int replyFloorId,
                             @RequestParam("detail") String detail,
                             @RequestParam("file") MultipartFile[] files,
                             HttpServletRequest request) {
        logger.info("detail " + detail);
        /*上传文件根路径*/
        String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload";
        /*对应url根路径*/
        String contextPath = request.getContextPath() + "/upload";
        return JSON.toJSONString(postService.createReply(accountId, postId, replyFloorId, detail, files, rootPath, contextPath));
    }




    @RequestMapping(value = "/seeReply", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String seeReply(@RequestParam("accountId") int accountId) {
        return JSON.toJSONString(postService.getReplyDataByAccountId(accountId));
    }


}
