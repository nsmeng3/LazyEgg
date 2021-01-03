package io.lazyegg.exception;

import io.lazyegg.util.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局过滤器
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/2 10:14 下午
 */
@Slf4j
public class GlobalFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
//        HttpServletResponse httpServletResponse = servletRequest;
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        s.setContentType("application/json;charset=utf-8");
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof NotLoggedInException) {
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                if (!request.getServletPath().contains("/login")) {
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    response.setContentType("application/json;charset=utf-8");
                    response.setHeader("Access-Control-Allow-Credentials", "true");
//                    response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
                    response.setHeader("Location", request.getRemoteHost() + ":" + request.getServerPort());
                    response.sendRedirect(((NotLoggedInException) cause).loginUrl());
                    return;
                }
            }
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {

    }
}
