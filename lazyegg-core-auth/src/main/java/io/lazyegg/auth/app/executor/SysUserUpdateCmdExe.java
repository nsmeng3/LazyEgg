package io.lazyegg.auth.app.executor;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.client.dto.SysUserUpdateCmd;
import io.lazyegg.auth.infrastructure.mapper.SysUserDO;
import io.lazyegg.auth.infrastructure.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/21 9:06 下午
 */
@Component
public class SysUserUpdateCmdExe {
    @Resource
    private SysUserMapper sysUserMapper;

    public Response execute(SysUserUpdateCmd cmd) {
        SysUserDO sysUserDO = new SysUserDO();
        BeanUtils.copyProperties(cmd.getSysUserCO(), sysUserDO);
        sysUserMapper.update(sysUserDO);
        return Response.buildSuccess();
    }
}
