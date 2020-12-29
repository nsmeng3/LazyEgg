package io.lazyegg.auth.app.executor;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.client.dto.OAuthTokenGetCmd;
import io.lazyegg.auth.domain.oauth.AssessToken;
import io.lazyegg.auth.domain.oauth.AuthorizationCode;
import org.springframework.stereotype.Component;

/**
 * 获取授权令牌命令执行
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 12:20 上午
 */
@Component
public class LeggOAuthTokenGetCmdExe {
    public Response execute(OAuthTokenGetCmd oAuthTokenGetCmd) {

        AuthorizationCode authorizationCode =
            new AuthorizationCode();

        // 检查GrantType
        authorizationCode.checkGrantType(oAuthTokenGetCmd.getGrantType());
        AssessToken assessToken = authorizationCode.genToken();
        return authorizationCode.issueToken(assessToken);

    }
}
