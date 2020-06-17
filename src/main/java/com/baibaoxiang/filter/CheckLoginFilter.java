package com.baibaoxiang.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问拦截  只对登录，登录验证，注册，注册验证，以及png,jpg,js,css为后缀的请求放行
 * @author sheng
 * @create 2019-05-06-23:10
 */
public class CheckLoginFilter implements Filter {
    /**
     * 不拦截的资源类型
     */
    private static String[] ignoreCheckURIs;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取到不需要校验的资源名称
        String ignoreCheckURI = filterConfig.getInitParameter("ignoreCheckURI");
        if (ignoreCheckURI != null && !ignoreCheckURI.trim().equals("")) {
            // 将所有的资源名存放到数组中,待后面进行校验
            ignoreCheckURIs = ignoreCheckURI.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        boolean isIgnoreURI = false;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 获取当前请求的资源名
        String url = req.getRequestURI();
        // 如果当前请求的资源是不需要校验的,直接放行
        if (ignoreCheckURIs != null) {
            for (int i = 0; i < ignoreCheckURIs.length; i++) {
                if (url.endsWith("." + ignoreCheckURIs[i])) {
                    isIgnoreURI = true;
                    break;
                }
            }
        }

        Object currentUser = req.getSession().getAttribute("username");
        if (url.endsWith("login") || url.endsWith("loginVerify")
                || currentUser != null || url.endsWith("userRegister")
                || url.endsWith("registerVerify") || url.endsWith("checkCode")
                || isIgnoreURI == true || url.endsWith("/login")
                || url.endsWith("/")) {
            filterChain.doFilter(req, resp);
            return;
        }

        if (currentUser == null) {
            resp.sendRedirect("login");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
