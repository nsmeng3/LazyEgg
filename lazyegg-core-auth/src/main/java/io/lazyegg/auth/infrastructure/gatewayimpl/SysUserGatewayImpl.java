package io.lazyegg.auth.infrastructure.gatewayimpl;

import com.alibaba.cola.exception.Assert;
import io.lazyegg.auth.domain.gateway.SysUserGateway;
import io.lazyegg.auth.domain.model.SysUser;
import io.lazyegg.auth.infrastructure.mapper.SysUserDO;
import io.lazyegg.auth.infrastructure.mapper.SysUserMapper;
import io.lazyegg.constants.ErrCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/19 11:28 下午
 */
@Service
public class SysUserGatewayImpl implements SysUserGateway {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUserId(Long userId) {
        SysUserDO sysUserDO = sysUserMapper.getByUserId(userId);
        SysUser sysUser = new SysUser();
        if (sysUserDO != null) {
            BeanUtils.copyProperties(sysUserDO, sysUser);
            sysUser.clearPassword();
        }
        return sysUser;
    }

    @Override
    public int save(SysUser sysUser) {
        SysUserDO target = new SysUserDO();
        BeanUtils.copyProperties(sysUser, target);
        int save = sysUserMapper.save(target);
        return save;
    }

    @Override
    public int delete(Long userId) {
        return sysUserMapper.delete(userId);
    }

    @Override
    public int update(SysUser sysUser) {
        SysUserDO target = new SysUserDO();
        BeanUtils.copyProperties(sysUser, target);
        return sysUserMapper.update(target);
    }

    @Override
    public List<String> getUserPermissions(Long userId) {
        return sysUserMapper.getUserPermissions(userId);
    }

    @Override
    public SysUser getByUsername(String username) {
        SysUserDO sysUserDO = sysUserMapper.getByUsername(username);
        Assert.notNull(sysUserDO, ErrCode.UserErr.UserLoginErr.A0201.name(), ErrCode.UserErr.UserLoginErr.A0201.getErrMessage());
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDO, sysUser);
        return sysUser;
    }

    @Override
    public List<String> getUserRoles(Long userId) {
        return sysUserMapper.getRoles(userId);
    }
}
