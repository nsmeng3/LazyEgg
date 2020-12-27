package io.lazyegg.auth.infrastructure.gatewayimpl;

import io.lazyegg.auth.domain.gateway.SysRoleGateway;
import io.lazyegg.auth.domain.model.SysRole;
import io.lazyegg.auth.infrastructure.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色网关实现
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:29 下午
 */
@Service
public class SysRoleGatewayImpl implements SysRoleGateway {

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public SysRole listRole(String username) {
        sysUserMapper.getByUserId(111L);
        return null;
    }
}
