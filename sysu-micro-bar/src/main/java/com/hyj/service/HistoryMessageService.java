package com.hyj.service;

import com.hyj.dto.HistoryData;

import java.util.List;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
public interface HistoryMessageService {
    /**
     * 检查是否有信息
     * @param accountId
     * @return
     */
    boolean checkMessage(Integer accountId);

    /**
     * 查看历史信息/（评论/回复）
     * @param accountId
     * @param currentMessageNum
     * @return
     */
    List<HistoryData> loadMessage(Integer accountId, Integer currentMessageNum);

}
