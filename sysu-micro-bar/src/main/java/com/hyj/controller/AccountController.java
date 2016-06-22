package com.hyj.controller;

import com.hyj.entity.Account;
import com.alibaba.fastjson.JSON;
import com.hyj.constant.Constants;
import com.hyj.dto.LoginData;
import com.hyj.dto.RegisterData;
import com.hyj.service.AccountService;
import com.hyj.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/14 0014.
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory
            .getLogger(AccountController.class);

    @Resource
    private AccountService accountService;

    /**
     * First thing to understand is that the RequestMapping#produces() element in

     @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "application/json")
     serves only to restrict the mapping for your request handlers. It does nothing else.

     Then, given that your method has a return type of String and is annotated with @ResponseBody, the return value will be handled by StringHttpMessageConverter
     which sets the Content-type header to text/plain. If you want to return a JSON string yourself and set the header to application/json, use a return type of
     ResponseEntity (get rid of @ResponseBody) and add appropriate headers to it.
     * @return
     */

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping(value = "/getCheckCode", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getCheckCode() {
        return accountService.getCheckCode();
    }


    /**
     * 登录操作
     * @param account
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public LoginData doLogin(@RequestBody Account account, ModelMap modelMap) {
        logger.info("登录操作");
        boolean result = false;
        try {
            logger.info(JSON.toJSONString(account));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (account.getStuNo() == null || account.getStuNo().trim().equals("") ||
                account.getPassword() == null || account.getPassword().trim().equals("")) {
            modelMap.addAttribute("erroMsg", Constants.LOGIN_FAIL_INVALID_INPUT);
            logger.error(Constants.LOGIN_FAIL_INVALID_INPUT);
            return new LoginData(null, result, Constants.LOGIN_FAIL_INVALID_INPUT);
        }
        account.setPassword(MD5.md5(account.getPassword()));
        /**
         * 获取从前台发送过来的学号与密码
         * 用学号来验证数据库是否有对应的记录
         */
        String stuNo = account.getStuNo();
        String password = account.getPassword();
        Account accountInDatabase = accountService.getAccountByStuNo(stuNo);
        if (accountInDatabase == null) {
            // 如果数据库里没有对应记录，说明还没有注册
            modelMap.addAttribute("erroMsg", Constants.LOGIN_FAIL_ACCOUNT_NOT_REGISTERED);
            logger.error(Constants.LOGIN_FAIL_ACCOUNT_NOT_REGISTERED);
            return new LoginData(null, result, Constants.LOGIN_FAIL_ACCOUNT_NOT_REGISTERED);
        } else {
            logger.info(accountInDatabase.getPassword());
            logger.info(password);
            if (password.equals(accountInDatabase.getPassword())) {
                // 如果从前端传送过来的密码等于数据库中传送的密码，那么登录成功
                modelMap.addAttribute("success", Constants.LOGIN_SUCCESS);
                logger.info(Constants.LOGIN_SUCCESS);
                result = true;
                return new LoginData(accountInDatabase, result, Constants.LOGIN_SUCCESS);
            } else {
                // 否则登录失败
                modelMap.addAttribute("erroMsg", Constants.LOGIN_FAIL_PASSWORD_ERROR);
                logger.error(Constants.LOGIN_FAIL_PASSWORD_ERROR);
                return new LoginData(null, result, Constants.LOGIN_FAIL_PASSWORD_ERROR);
            }
        }
    }


    /**
     * 注册操作，需要做的校验有: 账号查重，学号为空等
     * @param account
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public RegisterData doRgister(@RequestBody Account account, ModelMap modelMap) {
        logger.info("doRegister" + JSON.toJSONString(account));
        boolean result = false;
        if (StringUtils.isBlank(account.getStuNo())) {
//            throw new NullPointerException("学号不能为空");
            return new RegisterData(null, result, Constants.REGISTER_FAIL_INVALID_INPUT);
        }
        /*密码进行一次MD5的转换*/
        account.setPassword(MD5.md5(account.getPassword()));
        account.setRegisterTime(new Date());
        account.setAuthority(Constants.AUTH_USER);
        if (account.getId() == null) {
            result = accountService.register(account);
            account = accountService.getAccountByStuNo(account.getStuNo());
            return result ? new RegisterData(account, result, Constants.REGISTER_SUCCESS)
                          : new RegisterData(account, result, Constants.REGISTER_FAIL_ACCOUNT_REGISTERED);
        } else {
            logger.info("学号已经被注册");
//            throw new NullPointerException("学号已经被注册");
            return new RegisterData(null, result, Constants.REGISTER_FAIL_ACCOUNT_REGISTERED);
        }
    }





}
