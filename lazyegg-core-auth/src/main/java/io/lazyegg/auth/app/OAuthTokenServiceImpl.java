package io.lazyegg.auth.app;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.app.executor.AuthorizationCodeGetCmdExe;
import io.lazyegg.auth.app.executor.LeggOAuthTokenGetCmdExe;
import io.lazyegg.auth.client.OAuthTokenServiceI;
import io.lazyegg.auth.client.dto.AuthorizationCodeGetCmd;
import io.lazyegg.auth.client.dto.OAuthTokenGetCmd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/31 1:22 上午
 */

@Component
public class OAuthTokenServiceImpl implements OAuthTokenServiceI {
    @Resource
    private AuthorizationCodeGetCmdExe authorizationCodeGetCmdExe;
    @Resource
    private LeggOAuthTokenGetCmdExe oAuthTokenGetCmdExe;

    @Override
    public Response accessToken(OAuthTokenGetCmd cmd) {
        return oAuthTokenGetCmdExe.execute(cmd);
    }

    @Override
    public String authorizeRedirect(AuthorizationCodeGetCmd cmd) {
        return authorizationCodeGetCmdExe.execute(cmd);
    }
}
