package com.hyj.service.impl;

import com.hyj.constant.Constants;
import com.hyj.dao.*;
import com.hyj.dto.FloorData;
import com.hyj.dto.PostData;
import com.hyj.entity.*;
import com.hyj.service.PostFloorService;
import com.hyj.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
@Service("postService")
public class PostFloorServiceImpl implements PostFloorService {
    @Resource
    private PostMapper postMapper;
    @Resource
    private FloorMapper floorMapper;
    @Resource
    private FloorFileMapper floorFileMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private HistoryMessageMapper historyMessageMapper;


    private static final Logger logger = LoggerFactory
            .getLogger(PostFloorServiceImpl.class);


    public List<PostData> getPostDataList(int currentPostNum) {
        return transformPostsToPostDatas(postMapper.selectAllPost(currentPostNum));
    }

    public List<PostData> searchPostData(String title, Integer tag) {
        return transformPostsToPostDatas(postMapper.searchByTitleAndTag(title, tag));
    }

    public List<PostData> getPostByMyself(int accountId) {
        return transformPostsToPostDatas(postMapper.selectPostByCreator(accountId));
    }

    public List<PostData> getRecentPosts(int[] postIds) {
        List<Post> posts = new ArrayList<Post>();
        for (int postId : postIds) {
            posts.add(postMapper.selectByPrimaryKey(postId));
        }
        return transformPostsToPostDatas(posts);
    }


    /*查看回复..复制(若此条sql查询结果为空则是复制，否则是查看回复)*/
    // 查看回复选项需要两个参数
    // 1. post.id 2. floor.id
    // 从floor表中筛选出
    // 1. 不是回复的那些记录
    // 2. post_id与floor.getPost().getId()一致
    // 3. 比floor.id更晚被创建
    // 4. 那些符合1, 2, 3的所有floor的reply_floor_id中含有当前的floor.id
    // SELECT * FROM floor
    // WHERE
    // is_reply = false
    // AND post_id = #{post.id}
    // AND floor_id > #{floor.id}
    // AND reply_floor_id = #{floor.id}


    /**
     * 点击 任何一个floor，有三种情况需要考虑:
     * 1. 当floor属于回复的时候: 查看对话
     * (属于回复的时候，我们可以拿到当前回复的account(发表回复者A)和
     * 当前回复人的replyAccount(接收回复人B)，然后再通过数据表去查询出下列情况的记录:
     *  (1). 属于回复且包含A和B的楼(isReply == true && (account A replyAccount B || account B replyAccount B))
     *  (2). 不属于回复且只有A或者B发表的楼层...
     * 2. 当floor不属于回复的时候，有两种情况:
     *    1. 查看回复（当前的floor出现在后续floor的replyFloor字段中）
     *    2. 复制(当前的floor没有出现在后续floor的replyFloor字段中)
     *
     *
     *
     *
     * @param floorId
     * @return
     */
    public List<FloorData> getFloorInfo(int floorId) {
        Floor floor = floorMapper.selectByPrimaryKey(floorId);
        if (floor.getIsReply()) {

        } else {

        }
    }

    public List<FloorData> getAllFloorDatas(int postId) {
        return transformFloorsToFloorDatas(floorMapper.selectByPostId(postId));
    }


    /**
     * 私有工具函数，返回一个文件的url数组
     * @param dirName
     * @param fileBaseUrl
     * @return
     */
    private String[] uploadFiles(int floorId, String dirName, String fileBaseUrl, MultipartFile[] files) {
        logger.info("上传文件");
        String fileUrl /*+ 文件名*/;
        String[] fileUrls = new String[files.length];/*用于保存*/
        int count = 0;
        for (MultipartFile file : files) {
            fileUrl = fileBaseUrl;
            logger.info("文件长度: " + file.getSize());
            logger.info("文件类型: " + file.getContentType());
            logger.info("文件名称: " + file.getName());
            logger.info("文件原名: " + file.getOriginalFilename());
            logger.info("========================================");
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(dirName);
                if (!dir.exists())
                    dir.mkdirs();
                String filename = System.currentTimeMillis() + file.getOriginalFilename();
                File serverFile = new File(dir.getAbsolutePath() + "/" + filename);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
                // 将文件名添加到url中(用系统时间查重处理- -)
                fileUrl += filename;
                fileUrls[count++] = fileUrl;
                floorFileMapper.insertSelective(new FloorFile(floorId, fileUrl, Constants.TYPE_IMAGE));
            } catch (Exception e) {
                logger.error("You failed to upload => " + e.getMessage());
                return null;
            }
        }
        return fileUrls;
    }
    /**
     * 上传文件之后要把帖子的内容里的[img=uuid]替换成<img src="floor.getFileUrl()"></img>
     * */
    private void afterUploadFiles(String[] fileUrls, String content, Floor floor) {
        logger.info("上传文件");
        if (fileUrls != null) {
            String regex = "\\[img=\\w+-\\w+-\\w+-\\w+-\\w+\\]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            int count = 0;
            while (matcher.find()) {
                content = content.replace(matcher.group(), "\n<img src=\"" + fileUrls[count] + "\"</img>");
                logger.info(content);
                ++count;
            }
            floor.setDetail(content);
            floorMapper.updateByPrimaryKeySelective(floor);
        }
    }

    public boolean createPost(int accountId, String title, int tag, String detail, MultipartFile[] files, String rootPath, String contextPath) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Post post = new Post(account, title, tag, new Date(), new Date());
        postMapper.insertSelective(post);
        Floor floor = new Floor(post, account, null, false, new Date(), detail);
        logger.info("postId" + post.getId());
        floorMapper.insertSelective(floor);
        logger.info("floorId= =" + floor.getId());
        if (files != null && files.length != 0) {
            String dirName = rootPath + "/post" + post.getId() + "/floor" + floor.getId();
            String fileBaseUrl = contextPath + "/post" + post.getId() + "/floor" + floor.getId() + "/";
            String[] fileUrls = this.uploadFiles(floor.getId(), dirName, fileBaseUrl, files);
            this.afterUploadFiles(fileUrls, detail, floor);
        }
        return true;
    }

    public boolean createFloor(int accountId, int postId, String detail, MultipartFile[] files, String rootPath, String contextPath) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Post post = postMapper.selectByPrimaryKey(postId);
        Floor floor = new Floor(post, account, null, false, new Date(), detail);
        floorMapper.insertSelective(floor);
        String dirName = rootPath + "/post" + postId + "/floor" + floor.getId();
        String fileBaseUrl = contextPath + "/post" + postId + "/floor" + floor.getId() + "/";
        logger.info(dirName);
        logger.info(fileBaseUrl);
        if (files != null && files.length != 0) {
            String[] fileUrls = this.uploadFiles(floor.getId(), dirName, fileBaseUrl, files);
            afterUploadFiles(fileUrls, detail, floor);
        }
        // 盖楼：如果楼是我盖的话就不需要插入历史信息
        logger.info("创楼人" + floor.getAccount().getId());
        logger.info("创建人" + post.getCreator().getId());
        // 发布新的楼，并通知帖子的创建者
        if (floor.getAccount().getId() != post.getCreator().getId()) {
            logger.info("？？？" + floor.getId());
            HistoryMessage historyMessage = new HistoryMessage(post.getCreator(), floor, false, true);
            historyMessageMapper.insertSelective(historyMessage);
        }
        return true;
    }

    public boolean createReply(int accountId, int postId, int replyFloorId, String detail, MultipartFile[] files, String rootPath, String contextPath) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Post post = postMapper.selectByPrimaryKey(postId);
        Floor replyFloor = floorMapper.selectByPrimaryKey(replyFloorId);
        Floor floor = new Floor(post, account, replyFloor, true, new Date(), detail);
        floorMapper.insertSelective(floor);
        String dirName = rootPath + "/post" + postId + "/floor" + floor.getId();
        String fileBaseUrl = contextPath + "/post" + postId + "/floor" + floor.getId() + "/";
        if (files != null && files.length != 0) {
            String[] fileUrls = this.uploadFiles(floor.getId(), dirName, fileBaseUrl, files);
            afterUploadFiles(fileUrls, detail, floor);

        }
        // 回复某个人的时候，需要往历史消息表里插入两条记录
        // 一个是通知帖子的创始人
        // 另一个是通知回复的人
        // 如果回复的那个帖子的创始人是accountId的话就不用提醒
        if (post.getCreator().getId() != accountId) {
            HistoryMessage historyMessage1 = new HistoryMessage(post.getCreator(), floor, false, true);
            historyMessageMapper.insertSelective(historyMessage1);
        }
        // 如果回复的那层楼的帐号是accountId的话就不用提醒
        if (replyFloor.getAccount().getId() != post.getCreator().getId()) {
            HistoryMessage historyMessage2 = new HistoryMessage(replyFloor.getAccount(), floor, false, false);
            historyMessageMapper.insertSelective(historyMessage2);
        }
        return true;
    }






    private List<PostData> transformPostsToPostDatas(List<Post> posts) {
        List<PostData> postDatas = new ArrayList<PostData>();
        for (Post post : posts) {
            PostData postData = new PostData(post.getId(),
                    post.getTitle(), Constants.POST_TAGS[post.getTag()],
                    DateTimeUtil.getDateTimeString(post.getCreateTime()), floorMapper.selectCountByPostId(post.getId()));
            postDatas.add(postData);
        }
        return postDatas;
    }

    private List<FloorData> transformFloorsToFloorDatas(List<Floor> floors) {
        List<FloorData> floorDatas = new ArrayList<FloorData>();
        for (Floor floor : floors) {
            /*楼层id--->账号*/
            Account account = floor.getAccount();
            /*如果有回复，就增加replyAccount= =有可能空指针错误需要注意*/
            boolean isReply = floor.getIsReply();
            Floor replyFloor = floor.getReplyFloor();
            Account replyAccount = isReply ? replyFloor.getAccount() : null;
            FloorData floorData = new FloorData(floor.getId(), account.getHeadImageUrl(), account.getNickname(),
                    DateTimeUtil.getDateTimeString(floor.getCreateTime()), floor.getDetail(),
                    isReply, isReply == true ? replyAccount.getNickname() : null, isReply == true ? replyFloor.getId() : null);
            floorDatas.add(floorData);
        }
        return floorDatas;
    }


}
