package io.lazyegg.auth.infrastructure.config.simple;

import com.alibaba.cola.dto.Response;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * 用户名密码Filter
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/20 11:33 上午
 */
public class UsernamePasswordFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求username
        return getToken((HttpServletRequest) request);
    }


    @SneakyThrows
    private UsernamePasswordToken getToken(HttpServletRequest request) throws AuthorizationException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            String json = IOUtils.toString(request.getInputStream(), Charset.defaultCharset());
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject == null) {
                throw new RuntimeException("请登录");
            }
            username = jsonObject.getString("username");
            password = jsonObject.getString("password");
        }

        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            return new UsernamePasswordToken(username, password);
        } else {
            return new UsernamePasswordToken();
        }
    }

    /**
     * 是否是拒绝登录
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
//        httpResponse.setContentType("application/json;charset=utf-8");
//        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        Response res;
        if (e instanceof UnknownAccountException) {
            res = Response.buildFailure("400", "账号或密码不正确");
        } else if (e instanceof IncorrectCredentialsException) {
            res = Response.buildFailure("401", "账号或密码不正确");
        } else if (e instanceof LockedAccountException) {
            res = Response.buildFailure("402", "账号已被锁定,请联系管理员");
        } else {
            res = Response.buildFailure("500", e.getMessage());
        }
        httpResponse.getWriter().print(JSONObject.toJSONString(res));
        return false;
    }


}
