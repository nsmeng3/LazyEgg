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
public class AuthorizationCode {

    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String CODE = "code";

    private String grantType;
    private String clientId;
    private String clientSecret;
    private String scope;
    /**
     * 客户端生成的随机数，授权服务器回原样返回，防止CSRF攻击
     */
    private String state;

    public static HashMap<String, String> authorizationCodeCacheMap = new HashMap<>();

    public static AuthorizeBuilder authorize(AuthorizationRequest authorizationRequest) {
        return new AuthorizeBuilder(authorizationRequest);
    }


    @Getter
    public static class AuthorizeBuilder implements VerifyResponseType, VerifyScope, SetRedirectUri, ReceiveState, SendRedirectI {

        private String clientId;
        private String clientSecret;
        private String scope;
        private String state;
        private String redirectUri;
        private String responseType;

        public AuthorizeBuilder(AuthorizationRequest authorizationRequest) {
            if (authorizationRequest == null) {
                String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
                String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage()
                    + " - 参数[client_id]:值不存在";
                throw new BizException(errCode, errMessage);
            }
            this.responseType = authorizationRequest.getResponseType();
            this.clientId = authorizationRequest.getClientId();
            this.clientSecret = authorizationRequest.getClientSecret();
            this.scope = authorizationRequest.getScope();
            this.state = authorizationRequest.getState();
        }

        /**
         * 验证 response_type
         *
         * @return
         */
        @Override
        public VerifyScope verifyResponseType() {
            boolean checkResponseType = ResponseType.Code.equals(ResponseType.of(responseType));
            if (!checkResponseType) {
                String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
                String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage()
                    + "- 参数[response_type]";
                throw new BizException(errCode, errMessage);
            }
            return this;
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
        public ReceiveState setRedirectUri(String redirectUri) {
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
        public SendRedirectI receiveState(String state) {
            this.state = state;
            return this;
        }

        @Override
        public String sendRedirect() {
            // 生成code
            String code = UUID.randomUUID().toString();
            // 缓存
            authorizationCodeCacheMap.put(code, this.clientId + this.redirectUri);
            String redirectUri = this.redirectUri + "?code=" + code;
            if (state != null) {
                redirectUri += "&state=" + state;
            }
            return redirectUri;
        }
    }

    public static AccessTokenBuilder accessToken(AccessTokenRequest accessTokenRequest) {
        return new AccessTokenBuilder(accessTokenRequest);
    }

    @Getter
    public static class AccessTokenBuilder implements VerifyCode, GenAccessToken, ExpiredCode, IssueAccessToken {
        private String clientId;
        private String clientSecret;
        private String code;
        private String redirectUri;
        private String grantType;

        private AccessTokenResponse accessTokenResponse;

        public int i = 0;


        public AccessTokenBuilder(AccessTokenRequest accessTokenRequest) {
            this.grantType = accessTokenRequest.getGrantType();
            this.clientId = accessTokenRequest.getClientId();
            this.clientSecret = accessTokenRequest.getClientSecret();
            this.code = accessTokenRequest.getCode();
            this.redirectUri = accessTokenRequest.getRedirectUri();

        }

        public VerifyCode verifyGrantType() {
            boolean checkGrantType = GrantType.authorizationCode.equals(GrantType.of(grantType));
            if (!checkGrantType) {
                String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
                String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage()
                    + "- 错误参数[grant_type]值应为 " + AUTHORIZATION_CODE;
                throw new BizException(errCode, errMessage);
            }
            return this;
        }

        @Override
        public GenAccessToken verifyCode() {
            String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
            String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage() +
                " - 无效code值";
            Assert.isTrue(authorizationCodeCacheMap.containsKey(code), errCode, errMessage);
            return this;
        }

        /**
         * 生成令牌
         */
        @Override
        public ExpiredCode genAccessToken() {
            AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
            accessTokenResponse.setAccessToken("accessToken-vvvvvv");
            accessTokenResponse.setTokenType("bearer");
            accessTokenResponse.setExpiresIn(3600);
            accessTokenResponse.setRefreshToken("refreshToken-xxxxx");
            accessTokenResponse.setUid(String.valueOf(i));
            this.accessTokenResponse = accessTokenResponse;
            return this;
        }

        @Override
        public IssueAccessToken expiredCode() {
            authorizationCodeCacheMap.remove(code);
            return this;
        }

        @Override
        public AccessTokenResponse issueAccessToken() {
            i++;

            return this.accessTokenResponse;
        }
    }


}
