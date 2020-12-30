package io.lazyegg.auth.app.executor;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.lazyegg.auth.client.dto.OAuthTokenGetCmd;
import io.lazyegg.auth.domain.oauth.AccessTokenRequest;
import io.lazyegg.auth.domain.oauth.AccessTokenResponse;
import io.lazyegg.auth.domain.oauth.AuthorizationCode;
import org.springframework.stereotype.Component;

/**
 * 获取授权令牌命令执行
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 12:20 上午
 */
@Component
public class LeggOAuthTokenGetCmdExe {
    public Response execute(OAuthTokenGetCmd cmd) {

        // 检查GrantType
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setGrantType(cmd.getGrantType());
        accessTokenRequest.setClientId(cmd.getClientId());
        accessTokenRequest.setClientSecret(cmd.getClientSecret());
        accessTokenRequest.setCode(cmd.getCode());
        accessTokenRequest.setRedirectUri(cmd.getRedirectUri());

        AccessTokenResponse accessTokenResponse =
            AuthorizationCode.accessToken(accessTokenRequest)
                .verifyGrantType()
                .verifyCode()
                .genAccessToken().expiredCode().issueAccessToken();

        return SingleResponse.of(accessTokenResponse);

    }
}
