package io.lazyegg.auth.domain.gateway;

import io.lazyegg.auth.domain.model.SysRole;

/**
 * 角色网关
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:30 下午
 */
public interface SysRoleGateway {

    SysRole listRole(String username);
}
