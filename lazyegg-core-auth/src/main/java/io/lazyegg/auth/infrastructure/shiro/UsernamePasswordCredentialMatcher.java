package io.lazyegg.auth.infrastructure.shiro;

import io.lazyegg.auth.infrastructure.shiro.token.Token;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 自定义密码规则
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 4:30 下午
 */
@Component("credentialMatcher")
public class UsernamePasswordCredentialMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        DefaultPasswordService defaultPasswordService = new DefaultPasswordService();
        String p1 = defaultPasswordService.encryptPassword(token.getCredentials());
        Object credentials = info.getCredentials();

        String userName = (String) info.getPrincipals().getPrimaryPrincipal();
        Token statelessToken = (Token) token;
//        String password = String.valueOf(statelessToken.getPassword());
        return DigestUtils.md5DigestAsHex("admin".getBytes(StandardCharsets.UTF_8)).equals("");
    }


}
