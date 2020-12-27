package io.lazyegg.auth.app.executor.query;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.auth.client.dto.query.SysUserGetByUserIdQuery;
import io.lazyegg.auth.domain.gateway.SysUserGateway;
import io.lazyegg.auth.domain.model.SysUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/20 4:03 下午
 */
@Component
public class SysUserGetByUserIdQueryExe {

    @Resource
    private SysUserGateway sysUserGateway;

    public Response execute(SysUserGetByUserIdQuery query) {
        SysUser sysUser = sysUserGateway.getByUserId(query.getUserId());
        return SingleResponse.of(sysUser);
    }
}
