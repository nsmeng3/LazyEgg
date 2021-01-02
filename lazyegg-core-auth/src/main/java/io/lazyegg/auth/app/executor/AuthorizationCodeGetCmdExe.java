package io.lazyegg.auth.app.executor;

import com.alibaba.cola.dto.Command;
import com.alibaba.cola.exception.BizException;
import io.lazyegg.auth.client.dto.AuthorizationCodeGetCmd;
import io.lazyegg.auth.domain.oauth.ClientAppRegistrationInfo;
import io.lazyegg.auth.domain.gateway.oauth.AuthorizationCodeGateway;
import io.lazyegg.auth.domain.oauth.AuthorizationCode;
import io.lazyegg.constants.ErrCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 授权码获取
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/28 11:22 下午
 */
@Component
public class AuthorizationCodeGetCmdExe extends Command {

    @Resource
    private AuthorizationCodeGateway authorizationCodeGateway;

    public String execute(AuthorizationCodeGetCmd cmd) {

        // 获取client注册信息
        ClientAppRegistrationInfo info = authorizationCodeGateway.getClientRegistrationInfo(cmd.getClientId());
        // 生成回调地址
        return AuthorizationCode.authorize(info)
            .verifyResponseType(cmd.getResponseType())
            .verifyScope(cmd.getScope())
            .verifyRedirectUri(cmd.getRedirectUri())
            .receiveState(cmd.getState())
            .sendRedirect()
            ;
    }

    /**
     * 获取code
     *
     * @param authorizationCodeGetCmd
     * @return
     */
    private String getCode(AuthorizationCodeGetCmd authorizationCodeGetCmd) {
        return "12312312312";
    }

    /**
     * 获取携带code参数值的跳转地址
     *
     * @param code
     * @param redirectUri
     * @return
     */
    public static String callbackUrl(String code, String redirectUri) {
        if (StringUtils.isAnyBlank(code, redirectUri)) {
            throw new BizException(ErrCode.UserErr.UserReqParamErr.A0410.name(), ErrCode.UserErr.UserReqParamErr.A0410.getErrMessage());
        }
        if (redirectUri.contains("?")) {
            redirectUri = redirectUri.replaceAll("\\?", "");
        }
        return String.format("%s?%s", redirectUri, code);
    }
}
