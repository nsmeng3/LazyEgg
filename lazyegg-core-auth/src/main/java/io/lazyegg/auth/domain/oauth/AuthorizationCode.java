package io.lazyegg.auth.domain.oauth;

import com.alibaba.cola.domain.Entity;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import io.lazyegg.auth.domain.gateway.oauth.AuthorizationCodeGateway;
import io.lazyegg.constants.ErrCode;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Resource;

/**
 * 授权码模式
 *
 * 1. 检查grantType
 * 2. clientId、client_secret、code生成令牌
 * 3. 将code失效
 * 4. 颁发令牌
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 12:34 上午
 */
@Getter
@Setter
@Entity
public class AuthorizationCode extends OAuthToken {

    private static final String AUTHORIZATION_CODE = "authorization_code";

    @Resource
    private AuthorizationCodeGateway authorizationCodeGateway;

    private String clientSecret;
    private String code;

    public AuthorizationCode(String grantType, String clientId, String clientSecret, String code) {
        super(grantType, clientId);
        this.clientSecret = clientSecret;
        this.code = code;
    }

    @Override
    public void checkGrantType(String grantType) {
        boolean checkGrantType = GrantType.authorizationCode.equals(GrantType.of(grantType));
        if (!checkGrantType) {
            String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
            String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage()
                + "- 错误参数[grant_type]值应为 " + AUTHORIZATION_CODE;
            throw new BizException(errCode, errMessage);
        }
    }

    /**
     * 生成令牌
     */
    public AssessToken genToken() {
        String clientId = this.getClientId();
        String clientSecret = this.getClientSecret();
        String code = this.code;

        AssessToken assessToken = new AssessToken();
        assessToken.setAccessToken("1111");
        return assessToken;
    }

    /**
     * 颁发令牌
     */
    public SingleResponse<AssessToken> issueToken(AssessToken assessToken) {
        if (assessToken == null) {
            ErrCode.UserErr.UserReqServiceException a0500 = ErrCode.UserErr.UserReqServiceException.A0500;
            String errCode = a0500.name();
            String errMessage = a0500.getErrMessage();
            throw new BizException(errCode, errMessage);
        }
        return SingleResponse.of(assessToken);
    }


}
