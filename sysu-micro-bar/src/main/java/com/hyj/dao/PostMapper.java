package com.hyj.dao;

import com.hyj.entity.Post;

import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
    List<Post> searchByTitleAndTag(String title, int tag);
    List<Post> selectAllPost();


}