package io.lazyegg.auth.domain.gateway;

import io.lazyegg.auth.domain.model.SysUser;

import java.util.List;

/**
 * 系统用户网关
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 11:21 下午
 */
public interface SysUserGateway {

    SysUser getByUserId(Long userId);

    int save(SysUser sysUser);

    int delete(Long userId);

    int update(SysUser sysUser);

    List<String> getUserPermissions(Long userId);

    SysUser getByUsername(String username);

    List<String> getUserRoles(Long userId);
}
