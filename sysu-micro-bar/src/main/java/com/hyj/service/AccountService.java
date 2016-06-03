package com.hyj.service;

import com.hyj.entity.Account;

/**
 * Created by Administrator on 2016/5/14 0014.
 */
public interface AccountService {
    /**
     * 根据账号id查找用户
     * @param accountId
     * @return
     */
    public Account getAccountById(int accountId);
    /**
     * 获取校验码
     * @return
     */
    public String getCheckCode();
    /**
     * 注册某个账户，将查重逻辑写在这里
     * 如果学号重复，就返回false
     * 否则将对应的账户插入到表中
     * @param account
     * @return
     */
    public boolean register(Account account);
    /**
     * 通过学号来查找对应的账号
     * @param stuNo
     * @return
     */
    public Account getAccountByStuNo(String stuNo);
}
