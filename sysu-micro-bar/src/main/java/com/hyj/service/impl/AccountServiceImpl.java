package com.hyj.service.impl;

import com.alibaba.fastjson.JSON;
import com.hyj.dao.AccountMapper;
import com.hyj.entity.Account;
import com.hyj.service.AccountService;
import com.hyj.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public boolean uploadHeadImage(int accountId, MultipartFile file, String rootPath, String contextPath) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        if (file != null) {
            String dirName = rootPath + "/headImage/" + accountId;
            String fileBaseUrl = contextPath + "/headImage/" + accountId + "/";
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(dirName);
                if (!dir.exists())
                    dir.mkdir();
                String filename = System.currentTimeMillis() + file.getOriginalFilename();
                File serverFile = new File(dir.getAbsoluteFile() + "/" + filename);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
                account.setHeadImageUrl(fileBaseUrl + filename);
                accountMapper.updateByPrimaryKey(account);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("You failed to upload => " + e.getMessage());
            }
        }
        return false;
    }

    public boolean modifyPersonalInfo(int accountId, String nickname, String password) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        account.setNickname(nickname);
        account.setPassword(MD5.md5(password));
        accountMapper.updateByPrimaryKey(account);
        return true;
    }
}
