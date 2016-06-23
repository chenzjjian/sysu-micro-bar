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

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
@Controller
public class SidebarController {
    private static final Logger logger = LoggerFactory
            .getLogger(SidebarController.class);
    @Resource
    private PostFloorService postFloorService;
    @Resource
    private AccountService accountService;


    /**
     * 查看评论/回复
     */

    /**
     * 我发的帖
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/getPostByMyself", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<PostData> getPostByMyself(@RequestParam("accountId") int accountId) {
        return postFloorService.getPostByMyself(accountId);
    }


    /**
     * 修改资料: 需要对应的账号id，修改昵称以及修改密码
     * @param accountId
     * @param nickname
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "modifyPersonalInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean modifyPersonalInfo(@RequestParam("accountId") int accountId,
                                      @RequestParam("nickname") String nickname,
                                      @RequestParam("newPassword") String newPassword) {
        return accountService.modifyPersonalInfo(accountId, nickname, newPassword);
    }


    /**
     * 最近看过
     * @param postIds 最近看过的所有postId
     * @return
     */
    @RequestMapping(value = "/getRecentPost", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<PostData> getRecentPost(@RequestParam("postIds") int[] postIds) {
        return postFloorService.getRecentPosts(postIds);
    }


}
