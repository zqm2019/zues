package com.it.zqm.controller;

import com.it.zqm.exception.BaseException;
import com.it.zqm.utils.shiro.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe:
 * @author:zqm
 */
@RestController
public class UserController {
    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/userLogin")
    public String userLogin(HttpServletRequest request) {
        String phone = request.getParameter("userName");
        String password = request.getParameter("password");
        String result = "";
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        try {
            subject.login(token);
            result = "登陆成功";
        } catch (UnknownAccountException e) {
            result = "用户名不存在";
        } catch (IncorrectCredentialsException e) {
            result = "密码错误";
        }
        return result;
    }

    @RequestMapping("/exist")
    public String userExist(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();

        subject.logout();
        return "登出成功";

    }
    @RequiresPermissions("user:b")
    @RequestMapping("ll")
    public User testUserLogin(HttpServletRequest request) {
        // 获取当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        return user;
    }

    @RequiresPermissions("user:a")
    @RequestMapping("lll")
    public User testUserLogina(HttpServletRequest request) {
        // 获取当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        return user;
    }

    @RequiresRoles("admin")
    @RequestMapping("index")
    public User no(HttpServletRequest request) {
       throw  new BaseException("未登录");
    }
}
