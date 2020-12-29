package io.lazyegg.auth.domain.oauth;

import com.alibaba.cola.domain.Entity;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import io.lazyegg.constants.ErrCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 授权码模式
 * <p>
 * 1. 检查response_type
 * 2. 验证client_id
 * 3. scope验证
 * 4. 生成一次性code，使用后失效
 * <p>
 * 1. 检查grantType
 * 2. 验证clientId、client_secret、code
 * 3. 生成access_token等信息
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
    private static final String CODE = "code";

    private String clientSecret;
    private String scope;

    public static HashMap<String, String> authorizationCodeCacheMap = new HashMap<>();

    public static AuthorizeBuilder authorize(AuthorizationCode authorizationCode) {
        return new AuthorizeBuilder(authorizationCode);
    }

    /**
     * 验证 response_type
     *
     * @param responseType
     * @return
     */
    public static boolean verifyResponseType(String responseType) {
        boolean checkResponseType = ResponseType.Code.equals(ResponseType.of(responseType));
        if (!checkResponseType) {
            String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
            String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage()
                + "- 错误参数[response_type]值应为 " + CODE;
            throw new BizException(errCode, errMessage);
        }
        return true;
    }

    @Getter
    public static class AuthorizeBuilder implements VerifyScope, SetRedirectUri, SendRedirectI {

        private String clientId;
        private String clientSecret;
        private String scope;
        private String redirectUri;

        public AuthorizeBuilder(AuthorizationCode authorizationCode) {
            if (authorizationCode == null) {
                String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
                String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage()
                    + " - 参数[client_id]:值不存在";
                throw new BizException(errCode, errMessage);
            }
            this.clientId = authorizationCode.getClientId();
            this.clientSecret = authorizationCode.getClientSecret();
            this.scope = authorizationCode.getScope();
        }

        @Override
        public SetRedirectUri verifyScope(String scope) {
            String errorCode = ErrCode.UserErr.UserReqParamErr.A0410.name();
            String errMessage = ErrCode.UserErr.UserReqParamErr.A0410.getErrMessage()
                + " - 参数[scope]";
            Assert.notNull(scope, errorCode, errMessage);
            if (!this.scope.equals(scope)) {
                errorCode = ErrCode.UserErr.UserReqParamErr.A0420.name();
                errMessage = ErrCode.UserErr.UserReqParamErr.A0420.getErrMessage()
                    + " - 参数 [scope]";
                throw new BizException(errorCode, errMessage);
            }
            return this;
        }

        @Override
        public SendRedirectI setRedirectUri(String redirectUri) {
            String errorCode = ErrCode.UserErr.UserReqParamErr.A0410.name();
            String errMessage = ErrCode.UserErr.UserReqParamErr.A0410.getErrMessage()
                + " - 参数 [redirect_uri]";
            Assert.notNull(redirectUri, errorCode, errMessage);
            // 检查跳转地址是否合法
            boolean regxUri = Pattern.compile("^(http|https)://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\s*)?$").matcher(redirectUri).matches();
            if (!regxUri) {
                errorCode = ErrCode.UserErr.UserReqParamErr.A0420.name();
                errMessage = ErrCode.UserErr.UserReqParamErr.A0420.getErrMessage()
                    + " - 参数 [redirect_uri]:地址不合法";
                Assert.notNull(redirectUri, errorCode, errMessage);
            }
            this.redirectUri = redirectUri;
            return this;
        }

        @Override
        public String sendRedirect() {
            // 生成code
            String code = UUID.randomUUID().toString();
            // 缓存
            authorizationCodeCacheMap.put(code, this.clientId + this.redirectUri);
            return this.redirectUri + "?code=" + code;
        }

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
