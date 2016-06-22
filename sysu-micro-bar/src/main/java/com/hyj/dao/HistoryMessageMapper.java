package com.hyj.dao;

import com.hyj.entity.HistoryMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HistoryMessage record);

    int insertSelective(HistoryMessage record);

    HistoryMessage selectByPrimaryKey(Integer id);

    int selectCountByNotChecked(Integer accountId);

    int updateByPrimaryKeySelective(HistoryMessage record);

    int updateByPrimaryKey(HistoryMessage record);

    List<HistoryMessage> selectAllData(@Param("accountId")Integer accountId, @Param("currentMessageNum")Integer currentMessageNum);
}