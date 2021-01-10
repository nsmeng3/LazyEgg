package io.lazyegg.auth.infrastructure.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/10 3:04 上午
 */
public interface BaseToken extends AuthenticationToken {

    String getType();
}
