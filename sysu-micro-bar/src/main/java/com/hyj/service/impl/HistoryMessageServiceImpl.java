package com.hyj.service.impl;

import com.hyj.dao.HistoryMessageMapper;
import com.hyj.dto.HistoryData;
import com.hyj.entity.Account;
import com.hyj.entity.Floor;
import com.hyj.entity.HistoryMessage;
import com.hyj.entity.Post;
import com.hyj.service.HistoryMessageService;
import com.hyj.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
public class HistoryMessageServiceImpl implements HistoryMessageService {
    @Resource
    private HistoryMessageMapper historyMessageMapper;
    private static final Logger logger = LoggerFactory.getLogger(HistoryMessageMapper.class);
    public boolean checkMessage(Integer accountId) {
        return historyMessageMapper.selectCountByNotChecked(accountId) > 0;
    }
/*    public List<HistoryData> getReplyDataByAccountId(int accountId) {
        List<Floor> floors = floorMapper.selectByReply(accountId);
        List<HistoryData> historyDatas = new ArrayList<HistoryData>();
        for (Floor floor : floors) {
            Post post = floor.getPost();
            Account account = floor.getAccount();
            HistoryData replyData = new HistoryData(post.getId(), post.getTitle(), account.getNickname(), DateTimeUtil.getDateTimeString(floor.getCreateTime()));
            historyDatas.add(replyData);
        }
        return historyDatas;
    }*/


    public List<HistoryData> loadMessage(Integer accountId, Integer currentMessageNum) {
        List<HistoryData> historyDatas = new ArrayList<HistoryData>();
        List<HistoryMessage> historyMessages = historyMessageMapper.selectAllData(accountId, currentMessageNum);
        for (HistoryMessage historyMessage : historyMessages) {
            // 查看消息的时候把没有checked的全都设置为checked
            if (!historyMessage.getIsChecked())
                historyMessage.setIsChecked(true);
            Floor floor = historyMessage.getFloor();
            Account account = floor.getAccount();
            Post post = floor.getPost();
            HistoryData historyData = new HistoryData(post.getId(), post.getTitle(), account.getNickname(), DateTimeUtil.getDateTimeString(floor.getCreateTime()));
            historyDatas.add(historyData);
        }
        return historyDatas;
    }


}
