package io.lazyegg.auth.client;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.client.dto.JwtResponse;
import io.lazyegg.auth.client.dto.query.SysLoginByUsernamePasswordQry;
import io.lazyegg.auth.client.dto.query.UserInfoGetQry;

/**
 * 系统登录
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:10 下午
 */
public interface SysLoginServiceI {
    JwtResponse usernamePasswordLogin(SysLoginByUsernamePasswordQry sysLoginQuery);

    Response getUserInfo(UserInfoGetQry userInfoGetQry);
}
