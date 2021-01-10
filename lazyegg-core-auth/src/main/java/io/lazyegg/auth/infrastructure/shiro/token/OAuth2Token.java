package io.lazyegg.auth.infrastructure.shiro.token;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/29 10:10 下午
 */
public class OAuth2Token implements AuthenticationToken {

    private String accessToken;

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
