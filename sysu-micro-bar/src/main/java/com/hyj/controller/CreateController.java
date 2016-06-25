package com.hyj.controller;

import com.hyj.dto.PostData;
import com.hyj.service.AccountService;
import com.hyj.service.PostFloorService;
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
 * Created by Administrator on 2016/6/7 0007.
 */
@Controller
public class CreateController {

    private static final Logger logger = LoggerFactory
            .getLogger(CreateController.class);
    @Resource
    private PostFloorService postFloorService;
    @Resource
    private AccountService accountService;

    /**
     * 创建帖子
     * @param accountId
     * @param title
     * @param detail
     * @param tag
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/createPost", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public PostData createPost(@RequestParam("accountId") int accountId,
                               @RequestParam("title") String title,
                               @RequestParam("detail") String detail,
                               @RequestParam("tag") int tag,
                               @RequestParam("file") MultipartFile[] files,
                               HttpServletRequest request) {
        logger.info("detail " + detail);
        logger.info("accountId" + accountId);
        logger.info("TITLE" + title);
        logger.info("tag" + tag);
        logger.info("files的长度" + String.valueOf(files.length));
        /*上传文件根路径*/
        String rootPath = this.getRootPath(request);
        /*对应url根路径*/
        String contextPath = this.getContextPath(request);
        logger.info("rootPath " + rootPath);
        logger.info("contextPath " + contextPath);
        return postFloorService.createPost(accountId, title, tag, detail, files, rootPath, contextPath);
    }

    /**
     * 上传头像
     * @param accountId 账户id
     * @param file 文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadHeadImage", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean uploadHeadImage(@RequestParam("accountId") int accountId, @RequestParam("file")MultipartFile file, HttpServletRequest request) {
        /*上传文件根路径*/
        String rootPath = this.getRootPath(request);
        /*对应url根路径*/
        String contextPath = this.getContextPath(request);
        logger.info("rootPath " + rootPath);
        logger.info("contextPath " + contextPath);
        return this.accountService.uploadHeadImage(accountId, file, rootPath, contextPath);
    }


    private String getRootPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/") + "upload";
    }

    private String getContextPath(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return requestUrl.substring(0, requestUrl.lastIndexOf("/")) + "/upload";
    }

    /**
     * 发表评论
     * @param accountId
     * @param postId
     * @param detail
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/createFloor", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean createFloor(@RequestParam("accountId") int accountId,
                               @RequestParam("postId") int postId,
                               @RequestParam("detail") String detail,
                               @RequestParam("file") MultipartFile[] files,
                               HttpServletRequest request) {
        logger.info("detail " + detail);
        /*上传文件根路径*/
        String rootPath = this.getRootPath(request);
        /*对应url根路径*/
        String contextPath = this.getContextPath(request);
        logger.info("rootPath " + rootPath);
        logger.info("contextPath " + contextPath);
        return postFloorService.createFloor(accountId, postId, detail, files, rootPath, contextPath);
    }

    /**
     * 发表回复
     * @param accountId
     * @param postId
     * @param replyFloorId
     * @param detail
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/createReply", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean createReply(@RequestParam("accountId") int accountId,
                               @RequestParam("postId") int postId,
                               @RequestParam("replyFloorId") int replyFloorId,
                               @RequestParam("detail") String detail,
                               @RequestParam("file") MultipartFile[] files,
                               HttpServletRequest request) {
        logger.info("detail " + detail);
        /*上传文件根路径*/
        String rootPath = this.getRootPath(request);
        /*对应url根路径*/
        String contextPath = this.getContextPath(request);
        logger.info("rootPath " + rootPath);
        logger.info("contextPath " + contextPath);
        return postFloorService.createReply(accountId, postId, replyFloorId, detail, files, rootPath, contextPath);
    }
}
