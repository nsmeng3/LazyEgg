package io.lazyegg.auth.infrastructure.shiro.filter;

import io.lazyegg.util.ServletUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 用户名密码Filter
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/20 11:33 上午
 */
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HashMap<String, Object> params = ServletUtils.requestParams((HttpServletRequest) servletRequest);
        return new BearerToken("token");
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
