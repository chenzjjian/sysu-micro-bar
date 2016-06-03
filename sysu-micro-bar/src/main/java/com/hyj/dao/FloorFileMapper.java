package com.hyj.dao;

import com.hyj.entity.FloorFile;

import java.util.List;

public interface FloorFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FloorFile record);

    int insertSelective(FloorFile record);

    FloorFile selectByPrimaryKey(Integer id);

    List<FloorFile> selectByFloorId(Integer floorId);

    int updateByPrimaryKeySelective(FloorFile record);

    int updateByPrimaryKey(FloorFile record);
}