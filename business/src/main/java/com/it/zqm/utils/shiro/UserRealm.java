package com.it.zqm.utils.shiro;

/**
 * @describe:
 * @author:zqm
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {


    private final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 执行授权逻辑
     *
     * @param arg0
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roles = new HashSet<>();
        roles.add("admin");
        info.setRoles(roles);
        Set<String> permissions = new HashSet<>();
        permissions.add("user:a");
        info.setStringPermissions(permissions);

        //不授权先不写
        return info;
    }

    /**
     * 执行认证逻辑
     *
     * @param arg0
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        // 编写shiro判断逻辑，判断用户名和密码
        System.out.println("执行认证逻辑");
        // 判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        String username = token.getUsername();
        User user = new User();
        user.setPassword("111");
        user.setUserName(username);
        // 该用户不存在
        if (user == null) {
            // shiro底层会抛出UnKnowAccountException
            return null;
        }
        SecurityUtils.getSubject().getSession().setTimeout(60000);
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        long timeout = SecurityUtils.getSubject().getSession().getTimeout();

        // 判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }



}