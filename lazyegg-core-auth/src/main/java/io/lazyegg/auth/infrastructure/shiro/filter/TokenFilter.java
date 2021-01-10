package io.lazyegg.auth.infrastructure.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import io.lazyegg.auth.client.dto.JwtResponse;
import io.lazyegg.util.JwtUtils;
import io.lazyegg.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token 所有请求都过此过滤器，
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/9 8:26 下午
 */
@Slf4j
@Component
public class TokenFilter extends AuthorizationFilter {

    @Value("${token.secret}")
    private String secret;

    /**
     * 判断是否登录
     * 在登录的情况下会走此方法，此方法返回true直接访问控制器
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    /**
     * 表示访问拒绝时是否自己处理，
     * 如果返回 true 表示自己不处理且继续拦截器链执行，
     * 返回 false 表示自己已经处理了（比如重定向到另一个页面）。
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        String authorization = ServletUtils.getAuthorization(servletRequest);

        return authorization == null;
    }

    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        JwtResponse jwtResponse = JwtResponse.buildFailure("无效token");
        httpResponse.getWriter().print(JSONObject.toJSONString(jwtResponse));
    }

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilterInternal(request, response, chain);
    }
}
