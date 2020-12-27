package io.lazyegg.auth.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 11:29 下午
 */
@Mapper
public interface SysUserMapper {
    SysUserDO getByUserId(Long userId);

    List<String> getUserPermissions(Long userId);

    List<String> getRoles(Long userId);

    SysUserDO getByUsername(String username);

    int update(SysUserDO sysUser);

    int delete(Long userId);

    int save(SysUserDO sysUser);

    List<SysUserDO> list();
}
