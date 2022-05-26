package com.it.zqm.config;

/**
 * @describe:
 * @author:zqm
 */

import com.it.zqm.utils.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 */
@Configuration
@Lazy
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("");

        //添加Shiro内置过滤器


        Map<String, String> filterMap = new LinkedHashMap<>();
        // 要将登陆的接口放出来，不然没权限访问登陆的接口
        filterMap.put("/userLogin", "anon");
        // 授权过滤器
        // 当前授权拦截后，shiro会自动跳转到未授权页面
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
//        filterMap.put("/user/**", "authc");
//        filterMap.put("/case/**", "authc");
        filterMap.put("/*", "authc");


        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        /**
         *  身份认证失败，则跳转到登录页面的配置
         *  没有登录的用户请求需要登录的页面时自动跳转到登录页面，不是必须的属性，
         *  不输入地址的话会自动寻找项目web项目的根目录下的”/login.jsp”页面。
         *
         *  前后端分离项目 指定一个路径 专门处理包异常 让前端进行跳转
         *  也可使用自定义过滤器
         */
        shiroFilterFactoryBean.setLoginUrl("/index");

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        //自定义过滤
//        filters.put("authc",new ShiroLoginFilter());

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     * @param userRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     *
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }


    /**
     * 这里指定了动态代理的方式使用了 Cglib
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


    /**
     * 开启注解支持,包括 RequiresPermissions.class, RequiresRoles.class,
     * RequiresUser.class, RequiresGuest.class, RequiresAuthentication.class。
     *
     * 和Aop相同的逻辑，通过注入 Advisor 来增强一些类的和方
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver(){
        return new GlobalExceptionResolver();
    }
}