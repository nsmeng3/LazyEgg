package io.lazyegg.auth.infrastructure.config.simple;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import com.alibaba.fastjson.JSONObject;
import io.lazyegg.exception.NotLoggedInException;
import io.lazyegg.constants.ErrCode;
import io.lazyegg.constants.RequestParamType;
import io.lazyegg.util.RequestParamUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 用户名密码Filter
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/20 11:33 上午
 */
@Slf4j
public class UsernamePasswordFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求username
        return getToken((HttpServletRequest) request);
    }


    private UsernamePasswordToken getToken(HttpServletRequest request) throws Exception{

        HashMap<String, Object> params = RequestParamUtils.requestParams(request, RequestParamType.Query);
        String username = MapUtils.getString(params, "username");
        String password = MapUtils.getString(params, "password");

        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            return new UsernamePasswordToken(username, password);
        } else {
            log.info("未登录-调登录页");
            throw new NotLoggedInException(request);
        }
    }

    /**
     * 没有登录的情况下会走此方法
     * 返回true 会放行filter
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return executeLogin(request, response);
    }

    @SneakyThrows
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Response res;
        if (e.getCause() instanceof BaseException) {
            Throwable cause = e.getCause();
            res = Response.buildFailure(((BaseException) cause).getErrCode(), cause.getMessage());
        } else if (e instanceof UnknownAccountException) {
            res = Response.buildFailure(ErrCode.UserErr.UserLoginErr.A0201.name(), ErrCode.UserErr.UserLoginErr.A0201.getErrMessage());
        } else if (e instanceof IncorrectCredentialsException) {
            res = Response.buildFailure(ErrCode.UserErr.UserLoginErr.A0210.name(), ErrCode.UserErr.UserLoginErr.A0210.getErrMessage());
        } else if (e instanceof LockedAccountException) {
            res = Response.buildFailure(ErrCode.UserErr.UserLoginErr.A0202.name(), ErrCode.UserErr.UserLoginErr.A0202.getErrMessage());
        } else {
            res = Response.buildFailure(ErrCode.UserErr.UserLoginErr.A0220.name(), ErrCode.UserErr.UserLoginErr.A0220.getErrMessage());
        }
        httpResponse.getWriter().print(JSONObject.toJSONString(res));
        return false;
    }


}
