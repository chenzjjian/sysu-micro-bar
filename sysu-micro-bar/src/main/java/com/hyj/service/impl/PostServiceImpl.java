package com.hyj.service.impl;

import com.hyj.constant.Constants;
import com.hyj.dao.*;
import com.hyj.dto.FloorData;
import com.hyj.dto.PostData;
import com.hyj.dto.ReplyData;
import com.hyj.entity.*;
import com.hyj.service.PostService;
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
public class PostServiceImpl implements PostService {
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
            .getLogger(PostServiceImpl.class);

    public List<PostData> getPostDataList() {
        List<Post> posts = postMapper.selectAllPost();
        List<PostData> postDatas = new ArrayList<PostData>();
        for (Post post : posts) {
            PostData postData = new PostData(post.getId(),
                    post.getTitle(), Constants.POST_TAGS[post.getTag()],
                    DateTimeUtil.getDateTimeString(new Date()), floorMapper.selectCountByPostId(post.getId()));
            postDatas.add(postData);
        }
        return postDatas;
    }



    public List<PostData> searchPostData(String title, int tag) {
        List<Post> posts = postMapper.searchByTitleAndTag(title, tag);
        List<PostData> postDatas = new ArrayList<PostData>();
        for (Post post : posts) {
            PostData postData = new PostData(post.getId(),
                    post.getTitle(), Constants.POST_TAGS[post.getTag()],
                    DateTimeUtil.getDateTimeString(new Date()), floorMapper.selectCountByPostId(post.getId()));
            postDatas.add(postData);
        }
        return postDatas;
    }


    /**
     * 私有工具函数，返回一个文件的url数组
     * @param dirName
     * @param fileBaseUrl
     * @return
     */
    private String[] uploadFiles(int floorId, String dirName, String fileBaseUrl, MultipartFile[] files) {
        String fileUrl /*+ 文件名*/;
        String[] fileUrls = new String[]{};/*用于保存*/
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
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
                // 将文件名添加到url中(要做加密处理的话再说- -)
                fileUrl += file.getOriginalFilename();
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
        if (fileUrls != null) {
            String regex = "\\[img=\\w+-\\w+-\\w+-\\w+-\\w+\\]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            int count = 0;
            while (matcher.find()) {
                content.replace(matcher.group(), "\n<img src=\"" + fileUrls[count++] + "\"</img>");
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
        floorMapper.insertSelective(floor);
        String dirName = rootPath + File.separator + "post" + post.getId() + File.separator + "floor" + floor.getId();
        String fileBaseUrl = contextPath + File.separator + "post" + post.getId() + File.separator + "floor" + floor.getId() + File.separator;
        String[] fileUrls = this.uploadFiles(floor.getId(), dirName, fileBaseUrl, files);
        afterUploadFiles(fileUrls, detail, floor);
        return true;
    }

    public boolean createFloor(int accountId, int postId, String detail, MultipartFile[] files, String rootPath, String contextPath) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Post post = postMapper.selectByPrimaryKey(postId);
        Floor floor = new Floor(post, account, null, false, new Date(), detail);
        floorMapper.insertSelective(floor);
        String dirName = rootPath + File.separator + "post" + postId + File.separator + "floor" + floor.getId();
        String fileBaseUrl = contextPath + File.separator + "post" + postId + File.separator + "floor" + floor.getId() + File.separator;
        String[] fileUrls = this.uploadFiles(floor.getId(), dirName, fileBaseUrl, files);
        afterUploadFiles(fileUrls, detail, floor);
        HistoryMessage historyMessage = new HistoryMessage(post.getCreator(), floor, false);
        historyMessageMapper.insertSelective(historyMessage);
        return true;
    }

    public boolean createReply(int accountId, int postId, int replyFloorId, String detail, MultipartFile[] files, String rootPath, String contextPath) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Post post = postMapper.selectByPrimaryKey(postId);
        Floor replyFloor = floorMapper.selectByPrimaryKey(replyFloorId);
        Floor floor = new Floor(post, account, replyFloor, true, new Date(), detail);
        floorMapper.insertSelective(floor);
        String dirName = rootPath + File.separator + "post" + postId + File.separator + "floor" + floor.getId();
        String fileBaseUrl = contextPath + File.separator + "post" + postId + File.separator + "floor" + floor.getId() + File.separator;
        String[] fileUrls = this.uploadFiles(floor.getId(), dirName, fileBaseUrl, files);
        afterUploadFiles(fileUrls, detail, floor);
        HistoryMessage historyMessage = new HistoryMessage(post.getCreator(), floor, false);
        historyMessageMapper.insertSelective(historyMessage);
        return true;
    }


    public boolean hasNewMessage(int accountId) {
        boolean result = historyMessageMapper.selectCountByNotChecked(accountId) > 0;
        return result;
    }

    public List<ReplyData> getReplyDataByAccountId(int accountId) {
        List<Floor> floors = floorMapper.selectByReply(accountId);
        List<ReplyData> replyDatas = new ArrayList<ReplyData>();
        for (Floor floor : floors) {
            Post post = floor.getPost();
            Account account = floor.getAccount();
            ReplyData replyData = new ReplyData(post.getId(), post.getTitle(), account.getNickname(), DateTimeUtil.getDateTimeString(floor.getCreateTime()));
            replyDatas.add(replyData);
        }
        return replyDatas;
    }

    public List<FloorData> getAllFloorDatas(int postId) {
        List<Floor> floors = floorMapper.selectByPostId(postId);
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
                    isReply, isReply ? replyAccount.getNickname() : null, replyFloor.getId());
            floorDatas.add(floorData);
        }
        return floorDatas;
    }



}
