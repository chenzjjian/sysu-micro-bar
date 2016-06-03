package com.hyj.service;

import com.alibaba.fastjson.JSON;
import com.hyj.entity.Account;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2016/5/14 0014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class AccountServiceTest {

    private static Logger logger = Logger.getLogger(AccountServiceTest.class);
    @Resource
    private AccountService accountService = null;

    @Test
    public void getUserById() throws Exception {
        Account account = accountService.getAccountById(3);
        logger.info(JSON.toJSON(account));
    }

}