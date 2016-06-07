package com.hyj.dao;

import com.hyj.entity.Floor;

import java.util.List;

public interface FloorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Floor record);

    int insertSelective(Floor record);

    Floor selectByPrimaryKey(Integer id);

    int selectCountByPostId(Integer postId);

    List<Floor> selectByReply(Integer accountId);

    List<Floor> selectByPostId(Integer postId);

    int updateByPrimaryKeySelective(Floor record);

    int updateByPrimaryKey(Floor record);
}