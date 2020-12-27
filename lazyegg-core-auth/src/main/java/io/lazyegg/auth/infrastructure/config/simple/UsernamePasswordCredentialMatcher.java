package io.lazyegg.auth.infrastructure.config.simple;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 自定义密码规则
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 4:30 下午
 */
@Component("credentialMatcher")
public class UsernamePasswordCredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userName = (String) info.getPrincipals().getPrimaryPrincipal();
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = String.valueOf(usernamePasswordToken.getPassword());
        String s = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        return DigestUtils.md5DigestAsHex("admin".getBytes(StandardCharsets.UTF_8)).equals(s);
    }
}
