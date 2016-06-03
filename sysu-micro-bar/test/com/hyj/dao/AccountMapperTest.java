package com.hyj.dao;

import com.alibaba.fastjson.JSON;
import com.hyj.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/22 0022.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class AccountMapperTest {

    private static final Logger logger = LoggerFactory
            .getLogger(AccountMapperTest.class);

    @Resource
    private AccountMapper accountMapper;

    @Test
    public void deleteByPrimaryKey() throws Exception {
        accountMapper.deleteByPrimaryKey(1);
    }

    @Test
    public void insert() throws Exception {
        logger.info(JSON.toJSONString(true));
        String status = JSON.toJSONString(true);
        logger.info(status);
        Account account = new Account();
        account.setStuNo("13331095");
        account.setPassword("13331095");
        account.setNickname("黄勇进");
        account.setRegisterTime(new Date());
        accountMapper.insert(account);
    }

    @Test
    public void insertSelective() throws Exception {

        Account account = new Account();
        account.setStuNo("13331095");
        account.setPassword("13331095");
        account.setNickname("黄勇进");
        account.setRegisterTime(new Date());
        accountMapper.insertSelective(account);

    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

}