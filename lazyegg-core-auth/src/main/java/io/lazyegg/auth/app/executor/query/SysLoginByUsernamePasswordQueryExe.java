package io.lazyegg.auth.app.executor.query;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.auth.client.dto.clientobject.SysUserCO;
import io.lazyegg.auth.client.dto.query.SysLoginByUsernamePasswordQuery;
import io.lazyegg.auth.domain.gateway.SysUserGateway;
import io.lazyegg.auth.domain.model.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户名密码登录
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:16 下午
 */
@Component
public class SysLoginByUsernamePasswordQueryExe {

    @Autowired
    private SysUserGateway sysUserGateway;

    public SingleResponse<SysUserCO> execute(SysLoginByUsernamePasswordQuery query) {
        SysUser sysUser = sysUserGateway.getByUsername(query.getUsername());
        SysUserCO sysUserCO = new SysUserCO();
        BeanUtils.copyProperties(sysUser, sysUserCO);
        return SingleResponse.of(sysUserCO);
    }
}
