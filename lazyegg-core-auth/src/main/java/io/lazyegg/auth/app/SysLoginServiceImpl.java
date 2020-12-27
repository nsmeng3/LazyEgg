package io.lazyegg.auth.app;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.app.executor.query.SysLoginByUsernamePasswordQueryExe;
import io.lazyegg.auth.client.SysLoginServiceI;
import io.lazyegg.auth.client.dto.query.SysLoginByUsernamePasswordQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统登录
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:11 下午
 */
@Service
public class SysLoginServiceImpl implements SysLoginServiceI {

    @Resource
    private SysLoginByUsernamePasswordQueryExe sysLoginByUsernamePasswordQueryExe;

    @Override
    public Response usernamePasswordLogin(SysLoginByUsernamePasswordQuery query) {
        return sysLoginByUsernamePasswordQueryExe.execute(query);
    }
}
