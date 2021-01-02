package io.lazyegg.auth.infrastructure.gatewayimpl;

import io.lazyegg.auth.domain.oauth.ClientAppRegistrationInfo;
import io.lazyegg.auth.domain.gateway.oauth.AuthorizationCodeGateway;
import io.lazyegg.auth.domain.oauth.AccessTokenResponse;
import org.springframework.stereotype.Component;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/29 9:18 下午
 */
@Component
public class AuthorizationCodeGatewayImpl implements AuthorizationCodeGateway {
    @Override
    public String getRedirectUri(String clientId) {
        return "";
    }

    @Override
    public void issueToken(String redirectUri, AccessTokenResponse accessTokenResponse) {

    }

    @Override
    public ClientAppRegistrationInfo getClientRegistrationInfo(String clientId) {
        ClientAppRegistrationInfo info = new ClientAppRegistrationInfo();
        info.setClientId(clientId);
        info.setScope("read");
        info.setRedirectUri("http://localhost:6627/oauth/client/callback");
        return info;
    }
}
