package com.hyj.dao;

import com.hyj.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    List<Post> searchByTitleAndTag(@Param("title") String title, @Param("tag") Integer tag);

    List<Post> selectAllPost(Integer currentPostNum);


}