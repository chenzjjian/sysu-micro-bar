package com.hyj.controller;

import com.alibaba.fastjson.JSON;
import com.hyj.dto.FloorData;
import com.hyj.service.PostFloorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gangsterhyj on 16-6-23.
 */
public class FloorController {
    private static final Logger logger = LoggerFactory
            .getLogger(FloorController.class);
    @Resource
    private PostFloorService postFloorService;
    /**
     * 查看帖子详情(暂时不考虑下拉刷新)
     * @param postId
     * @return
     */
    @RequestMapping(value = "/getPostDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<FloorData> getPostDetail(@RequestParam("postId") int postId) {
        logger.info(JSON.toJSONString(postFloorService.getAllFloorDatas(postId)));
        return postFloorService.getAllFloorDatas(postId);
    }


    @RequestMapping(value = "/seeSpecificFloor", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<FloorData> getFloorInfo(@RequestParam("floorId") int floorId) {
        return postFloorService.getFloorInfo(floorId);
        // return postFloorService.getAllFloorDatas(floorId);
    }

}
