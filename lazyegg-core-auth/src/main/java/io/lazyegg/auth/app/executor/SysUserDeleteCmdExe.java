package io.lazyegg.auth.app.executor;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.client.dto.SysUserDeleteCmd;
import io.lazyegg.auth.infrastructure.mapper.SysUserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/21 9:57 下午
 */
@Component
public class SysUserDeleteCmdExe {
    @Resource
    private SysUserMapper sysUserMapper;

    public Response execute(SysUserDeleteCmd sysUserDeleteCmd) {
        sysUserMapper.delete(sysUserDeleteCmd.getUserId());
        return Response.buildSuccess();
    }
}
