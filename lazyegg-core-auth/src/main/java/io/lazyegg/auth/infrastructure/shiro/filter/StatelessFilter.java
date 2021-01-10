package io.lazyegg.auth.infrastructure.shiro.filter;

import com.alibaba.cola.dto.Response;
import com.alibaba.fastjson.JSONObject;
import io.lazyegg.auth.client.SysLoginServiceI;
import io.lazyegg.auth.client.dto.JwtResponse;
import io.lazyegg.auth.client.dto.query.SysLoginByUsernamePasswordQry;
import io.lazyegg.util.ServletUtils;
import io.lazyegg.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无状态Filter
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/20 11:33 上午
 */
@Slf4j
@Component
public class StatelessFilter extends AccessControlFilter {


    public StatelessFilter() {
        log.info("init {}", this);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest(request);
//        if (!httpServletRequest.getMethod().equalsIgnoreCase(POST_METHOD)) {
//            onLoginFail(response);
//            return false;
//        }
        // 接收登录参数
        SysLoginByUsernamePasswordQry usernamePasswordQuery = ServletUtils.requestParams(httpServletRequest, SysLoginByUsernamePasswordQry.class);
        SysLoginServiceI sysLoginService = SpringUtils.getBean(SysLoginServiceI.class);
        // 5、委托给Realm进行登录
        JwtResponse jwtResponse = sysLoginService.usernamePasswordLogin(usernamePasswordQuery);
        if (jwtResponse.isSuccess()) {
            return true;
        } else {
            onLoginFail(response);
            return false;
        }

    }

    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        JwtResponse jwtResponse = JwtResponse.buildFailure("登录失败");
        httpResponse.getWriter().print(JSONObject.toJSONString(jwtResponse));
    }

    private void onLoginSuccess(ServletResponse response, Response jwtResponse) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.getWriter().print(JSONObject.toJSONString(jwtResponse));
    }

}
