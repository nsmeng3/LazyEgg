package io.lazyegg.auth.infrastructure.gatewayimpl;

import io.lazyegg.auth.domain.gateway.oauth.AuthorizationCodeGateway;
import io.lazyegg.auth.domain.oauth.AssessToken;
import io.lazyegg.auth.domain.oauth.AuthorizationCode;
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
    public void issueToken(String redirectUri, AssessToken assessToken) {

    }

    @Override
    public AuthorizationCode getClientRegistrationInfo(String clientId) {
        AuthorizationCode authorizationCode = new AuthorizationCode();
        authorizationCode.setClientId(clientId);
        authorizationCode.setClientSecret("secret");
        authorizationCode.setScope("read");
        return authorizationCode;
    }
}
