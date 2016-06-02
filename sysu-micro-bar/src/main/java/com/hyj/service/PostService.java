package com.hyj.service;

import com.hyj.dto.FloorData;
import com.hyj.dto.PostData;
import com.hyj.dto.ReplyData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public interface PostService {
    /**
     * 获取数据库中按创建时间排序的所有post数据
     * @return
     */
    public List<PostData> getPostDataList();

    /**
     * 根据标题关键字和标签进行搜索
     * @param title
     * @param tag
     * @return
     */
    public List<PostData> searchPostData(String title, int tag);

    /**
     *  回复
     * @param accountId
     * @param postId
     * @param replyFloorId
     * @param detail
     * @param files
     * @param rootPath
     * @param contextPath
     * @return
     */
    public boolean createReply(int accountId, int postId, int replyFloorId, String detail, MultipartFile[] files, String rootPath, String contextPath);

    /**
     * 盖楼
     * @param accountId
     * @param postId
     * @param detail
     * @param files
     * @param rootPath
     * @param contextPath
     * @return
     */
    public boolean createFloor(int accountId, int postId, String detail, MultipartFile[] files, String rootPath, String contextPath);
    /**
     * 用户accountId创建帖子，并上传文件到根路径下
     * 创建帖子,rootPath表示根路径(实际地址),contextPath表示上下文路径(url地址)
     * @param accountId
     * @param title
     * @param tag
     * @param detail
     * @param files
     * @param rootPath
     * @param contextPath
     * @return
     */
    public boolean createPost(int accountId, String title, int tag, String detail, MultipartFile[] files, String rootPath, String contextPath);
    /**
     * 根据帖子id获取所有楼层
     * @param postId
     * @return
     */
    public List<FloorData> getAllFloorDatas(int postId);

    /**
     * 给定用户账户id查看回复/评论
     * @param accountId
     * @return
     */
    public List<ReplyData> getReplyDataByAccountId(int accountId);
}
