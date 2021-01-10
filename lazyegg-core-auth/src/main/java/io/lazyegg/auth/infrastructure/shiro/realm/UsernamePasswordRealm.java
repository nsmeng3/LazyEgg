package io.lazyegg.auth.infrastructure.shiro.realm;

import io.lazyegg.auth.infrastructure.mapper.SysUserMapper;
import io.lazyegg.auth.infrastructure.shiro.token.Token;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * 用户名密码Realm
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/10 2:06 上午
 */
@Component
public class UsernamePasswordRealm extends AuthorizingRealm {

    private static final long EXPIRED_MILLIS = 1000 * 1000 * 30;
    private static final String REALM_NAME = "leggStatelessRealm";

    private SysUserMapper sysUserMapper;

    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public String getName() {
        return REALM_NAME;
    }

    @Override
    public boolean isCachingEnabled() {
        return false;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        authorizationInfo.addRole("admin");
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        StatelessToken statelessToken = (StatelessToken) token;
        Object principal = token.getPrincipal();
        String password = null;
        if (principal instanceof String) {
            password = sysUserMapper.getPasswordByUsername((String) principal);
        }
        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(principal, password, getName());
    }

}
