package com.hyj.service;

import com.hyj.entity.Account;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2016/5/14 0014.
 */
public interface AccountService {
    /**
     * 根据账号id查找用户
     * @param accountId
     * @return
     */
    Account getAccountById(int accountId);
    /**
     * 获取校验码
     * @return
     */
    String getCheckCode();
    /**
     * 注册某个账户，将查重逻辑写在这里
     * 如果学号重复，就返回false
     * 否则将对应的账户插入到表中
     * @param account
     * @return
     */
    boolean register(Account account);
    /**
     * 通过学号来查找对应的账号
     * @param stuNo
     * @return
     */
    Account getAccountByStuNo(String stuNo);

    boolean uploadHeadImage(int accountId, MultipartFile file, String rootPath, String contextPath);

    boolean modifyPersonalInfo(int accountId, String nickname, String password);
}
