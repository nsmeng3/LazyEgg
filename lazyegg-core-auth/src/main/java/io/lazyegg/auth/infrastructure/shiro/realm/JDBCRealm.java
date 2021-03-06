package io.lazyegg.auth.infrastructure.shiro.realm;

import com.alibaba.cola.exception.BizException;
import io.lazyegg.auth.domain.gateway.SysUserGateway;
import io.lazyegg.auth.domain.model.SysUser;
import io.lazyegg.constants.ErrCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义realm
 *
 * @author shengwu ni
 */

@Slf4j
@Component
public class JDBCRealm extends JdbcRealm {




    public static final String REALM_NAME = "leggJdbcRealm";

    final SysUserGateway sysUserGateway;

    public JDBCRealm(SysUserGateway sysUserGateway) {
        this.sysUserGateway = sysUserGateway;
    }

    @Override
    public String getName() {
        return REALM_NAME;
    }

    /**
     * 授权-验证权限时调用
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名
        SysUser principal = (SysUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 给该用户设置角色，角色信息存在 t_role 表中取
        authorizationInfo.setRoles(getRoles(principal.getUserId()));
        // 给该用户设置权限，权限信息存在 t_permission 表中取
        authorizationInfo.setStringPermissions(getPermissions(principal.getUserId()));
        return authorizationInfo;
    }

    private Set<String> getRoles(Long userId) {
        List<String> userRoles = sysUserGateway.getUserRoles(userId);
        return new HashSet<>(userRoles);
    }

    /**
     * 获取权限
     *
     * @param userId
     * @return
     */
    private Set<String> getPermissions(Long userId) {
        List<String> userPermissions = sysUserGateway.getUserPermissions(userId);
        Set<String> set = new HashSet<>();
        for (String userPermission : userPermissions) {
            if (StringUtils.isBlank(userPermission)) {
                continue;
            }
            set.addAll(Arrays.asList(userPermission.trim().split(",")));
        }
        return set;
    }

    /**
     * 认证-登录时调用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("JDBCRealm doGetAuthenticationInfo processing...");
        // 根据 Token 获取用户名，如果您不知道该 Token 怎么来的，先可以不管，下文会解释
        String username = (String) authenticationToken.getPrincipal();
        if (StringUtils.isBlank(username)) {
            throw new BizException(ErrCode.UserErr.UserLoginErr.A0210.name(), ErrCode.UserErr.UserLoginErr.A0210.getErrMessage());
        }
        // 根据用户名从数据库中查询该用户
        SysUser user = getByUsername(username);
        if (user != null) {
            // 获取权限列表
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getUsername(), REALM_NAME);
            // 清除密码
            user.clearPassword();
            // 传入用户名和密码进行身份认证，并返回认证信息
            return authcInfo;
        } else {
            throw new BizException(ErrCode.UserErr.UserLoginErr.A0201.name(), ErrCode.UserErr.UserLoginErr.A0201.getErrMessage());
        }
    }

    private SysUser getByUsername(String username) {
        return sysUserGateway.getByUsername(username);
    }


}
