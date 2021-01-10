package io.lazyegg.auth.infrastructure.shiro.realm;

import com.alibaba.cola.exception.BizException;
import io.lazyegg.auth.domain.gateway.SysUserGateway;
import io.lazyegg.auth.domain.model.SysUser;
import io.lazyegg.auth.infrastructure.shiro.token.Token;
import io.lazyegg.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Value;
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
public class TokenRealm extends AuthorizingRealm {
    public static final String REALM_NAME = "leggTokenRealm";

    @Value("${token.secret}")
    private String secret;

    final SysUserGateway sysUserGateway;

    public TokenRealm(SysUserGateway sysUserGateway) {
        this.sysUserGateway = sysUserGateway;
    }

    @Override
    public String getName() {
        return REALM_NAME;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token;
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

    /**
     * 认证-登录时调用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("token filter ...");
        String authorization = (String) authenticationToken.getPrincipal();
        if (StringUtils.isBlank(authorization)) {
            throw new BizException("authorization is null");
        }
        try {
            JwtUtils.parseJWT(authorization, secret);
            return new SimpleAuthenticationInfo(authorization, authorization, REALM_NAME);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new SimpleAuthenticationInfo();
        }
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

    private SysUser getByUsername(String username) {
        return sysUserGateway.getByUsername(username);
    }


}
