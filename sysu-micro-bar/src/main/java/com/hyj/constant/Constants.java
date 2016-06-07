package com.hyj.constant;

/**
 * Created by Administrator on 2016/5/22 0022.
 */
public class Constants {
    /**
     * 注册成功
     */
    public static  String REGISTER_SUCCESS = "注册成功";
    /**
     * 注册失败，学号可能被注册
     */
    public static  String REGISTER_FAIL_ACCOUNT_REGISTERED = "学号已经被注册";
    /**
     * 注册失败，学号不能为空
     */
    public static  String REGISTER_FAIL_INVALID_INPUT = "学号不能为空";

    /**
     * 登录成功
     */
    public static  String LOGIN_SUCCESS = "登录成功";
    /**
     * 登录失败：非法用户名或密码名
     */
    public static  String LOGIN_FAIL_INVALID_INPUT = "用户名或者密码为空";
    /**
     * 登录失败：用户已被注册
     */
    public static  String LOGIN_FAIL_ACCOUNT_NOT_REGISTERED = "用户还没注册";
    /**
     * 登录失败：密码不匹配
     */
    public static  String LOGIN_FAIL_PASSWORD_ERROR = "密码不匹配";

    /**
     * 用户身份
     */
    public static final int AUTH_USER = 0;
    /**
     * 管理员身份
     */
    public static final int AUTH_MANAGER = 1;
    /**
     *  帖子标签
     */
    public static final String[] POST_TAGS = new String[]{"学习", "运动", "交通", "美食", "运动", "其他"};
    /**
     * 文件类型: 1表示图片，0表示被删除
     */
    public static final int TYPE_DELETED = 0;
    public static final int TYPE_IMAGE = 1;
}
