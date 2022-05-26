package com.it.zqm.utils.shiro;

import com.alibaba.fastjson.JSON;
import com.it.zqm.result.Result;
import com.it.zqm.result.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.apache.shiro.web.util.WebUtils.SAVED_REQUEST_KEY;

/**
 * @describe:
 * @author:zqm
 */
public class ShiroLoginFilter extends FormAuthenticationFilter {

    /**
     * 如果isAccessAllowed返回false 则执行onAccessDenied
     * 会判断是否已登录
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //这里是个坑，如果不设置的接受的访问源，那么前端都会报跨域错误，因为这里还没到corsConfig里面
        httpServletResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter out = httpServletResponse.getWriter();

        out.println(JSON.toJSONString( ResultGenerator.getFailResult(4011,"未登录!")));
        out.flush();
        out.close();
        return false;

    }


    public static SavedRequest getSavedRequest(ServletRequest request) {
        SavedRequest savedRequest = null;
        SecurityUtils.getSubject().getPrincipal();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session != null) {
            savedRequest = (SavedRequest) session.getAttribute(SAVED_REQUEST_KEY);
        }
        return savedRequest;
    }
}
