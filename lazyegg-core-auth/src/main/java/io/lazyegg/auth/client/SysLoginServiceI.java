package io.lazyegg.auth.client;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.client.dto.query.SysLoginByUsernamePasswordQuery;

/**
 * 系统登录
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:10 下午
 */
public interface SysLoginServiceI {
    Response usernamePasswordLogin(SysLoginByUsernamePasswordQuery sysLoginQuery);
}
