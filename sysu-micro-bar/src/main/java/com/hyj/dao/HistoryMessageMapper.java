package com.hyj.dao;

import com.hyj.entity.HistoryMessage;

public interface HistoryMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HistoryMessage record);

    int insertSelective(HistoryMessage record);

    HistoryMessage selectByPrimaryKey(Integer id);

    int selectCountByNotChecked(Integer accountId);

    int updateByPrimaryKeySelective(HistoryMessage record);

    int updateByPrimaryKey(HistoryMessage record);
}