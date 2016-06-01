package com.hyj.service.impl;

import com.alibaba.fastjson.JSON;
import com.hyj.dao.AccountMapper;
import com.hyj.entity.Account;
import com.hyj.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/14 0014.
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    public Account getAccountById(int accountId) {
        return this.accountMapper.selectByPrimaryKey(accountId);
    }
    private static final Logger logger = LoggerFactory
            .getLogger(AccountServiceImpl.class);

    public String getCheckCode() {
        // 生成四位随机数
        long code = Math.round(Math.random() * 8000 + 2000);
        return JSON.toJSONString(code);    }


    public boolean register(Account account) {
        if (this.accountMapper.selectByStuNo(account.getStuNo()) == null) {
            this.accountMapper.insertSelective(account);
            this.logger.info("注册用户成功");
        } else {
            this.logger.error("学号重复，请再选择一个学号");
            return false;
        }
        return true;
    }


    public Account getAccountByStuNo(String stuNo) {
        return this.accountMapper.selectByStuNo(stuNo);
    }
}
