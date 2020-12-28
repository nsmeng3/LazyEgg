package io.lazyegg.auth.domain.gateway.oauth;

import io.lazyegg.auth.domain.oauth.AssessToken;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/29 1:13 上午
 */
public interface AuthorizationCodeGateway {

    /**
     * 获取跳转地址 - 令牌接收地址
     *
     * @param clientId
     * @return
     */
    String getRedirectUri(String clientId);

    /**
     * 颁发令牌
     *
     * @param redirectUri
     * @param assessToken
     */
    void issueToken(String redirectUri, AssessToken assessToken);
}
