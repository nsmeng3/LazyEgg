package io.lazyegg.auth.adapter.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.auth.client.SysLoginServiceI;
import io.lazyegg.auth.client.dto.clientobject.SysUserCO;
import io.lazyegg.auth.client.dto.query.SysLoginByUsernamePasswordQry;
import io.lazyegg.auth.client.dto.query.UserInfoGetQry;
import io.lazyegg.constants.RequestParamType;
import io.lazyegg.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统登录
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/17 10:44 下午
 */
@RestController
@RequestMapping("/")
public class SysLoginController extends BaseController {

    @Resource
    private SysLoginServiceI sysLoginService;

    /**
     * 登录
     *
     * @return
     */
    @GetMapping("/session")
    public Response login() {
        SysUserCO sysUserCO = requestParams(SysUserCO.class, RequestParamType.AllParam);
        SysLoginByUsernamePasswordQry sysLoginQuery = new SysLoginByUsernamePasswordQry(sysUserCO.getUsername(), sysUserCO.getPassword());
        return sysLoginService.usernamePasswordLogin(sysLoginQuery);
    }

    @GetMapping("/user-info")
    public Response getUserInfo() {
        String authorization = getAuthorization(getRequest());
        UserInfoGetQry userInfoGetQry = new UserInfoGetQry(authorization);
        return sysLoginService.getUserInfo(userInfoGetQry);
    }

    @GetMapping("/logout")
    public Response logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Response.buildSuccess();
    }

    @GetMapping("/index")
    public SingleResponse<String> index() {

        return SingleResponse.of("欢迎登陆");
    }

    @RequestMapping("/error/rethrow")
    public Response rethrow() {
        return Response.buildFailure("404", "404");
    }
}
