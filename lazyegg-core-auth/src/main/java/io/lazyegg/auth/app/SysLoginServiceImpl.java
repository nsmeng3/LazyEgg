package io.lazyegg.auth.app;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.app.executor.query.SysLoginByUsernamePasswordQryExe;
import io.lazyegg.auth.app.executor.query.UserInfoGetQryExe;
import io.lazyegg.auth.client.SysLoginServiceI;
import io.lazyegg.auth.client.dto.JwtResponse;
import io.lazyegg.auth.client.dto.query.SysLoginByUsernamePasswordQry;
import io.lazyegg.auth.client.dto.query.UserInfoGetQry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统登录
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:11 下午
 */
@Slf4j
@Service
public class SysLoginServiceImpl implements SysLoginServiceI {

    public SysLoginServiceImpl() {
        log.info("init {}", this);
    }

    @Resource
    private SysLoginByUsernamePasswordQryExe sysLoginByUsernamePasswordQryExe;
    @Resource
    private UserInfoGetQryExe userInfoGetQryExe;

    @Override
    public JwtResponse usernamePasswordLogin(SysLoginByUsernamePasswordQry query) {
        return sysLoginByUsernamePasswordQryExe.execute(query);
    }

    @Override
    public Response getUserInfo(UserInfoGetQry userInfoGetQry) {

        return userInfoGetQryExe.execute(userInfoGetQry);
    }
}
