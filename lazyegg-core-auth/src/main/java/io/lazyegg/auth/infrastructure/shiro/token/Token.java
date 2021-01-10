package io.lazyegg.auth.infrastructure.shiro.token;

import lombok.Getter;
import lombok.Setter;

/**
 * 无状态token
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/10 1:44 上午
 */
@Getter
@Setter
public class Token implements BaseToken {
    private String authorization;

    public Token(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public String getType() {
        return "statelessToken";
    }

    @Override
    public Object getPrincipal() {
        return this.authorization;
    }

    @Override
    public Object getCredentials() {
        return this.authorization;
    }
}
