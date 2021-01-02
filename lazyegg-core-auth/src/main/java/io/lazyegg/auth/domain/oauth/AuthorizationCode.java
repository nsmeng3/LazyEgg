package io.lazyegg.auth.domain.oauth;

import com.alibaba.cola.domain.Entity;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import io.lazyegg.auth.client.dto.OAuthResponse;
import io.lazyegg.constants.ErrCode;
import io.lazyegg.auth.infrastructure.exception.OAuthException;
import io.lazyegg.exception.NotFound404Exception;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.UUID;

/**
 * 授权码模式
 * <p>
 * 1. 检查grantType
 * 2. 验证clientId、client_secret、code
 * 3. 生成access_token等信息
 * 3. 将code失效
 * 4. 颁发令牌
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 12:34 上午
 */
@Slf4j
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
    private String redirectUri;
    /**
     * 客户端生成的随机数，授权服务器回原样返回，防止CSRF攻击
     */
    private String state;

    public static HashMap<String, String> authorizationCodeCacheMap = new HashMap<>();

    /**
     * 获取授权码
     * <p>
     * 1. 检查response_type
     * 2. 验证client_id
     * 3. scope验证
     * 4. 生成一次性code，使用后失效
     *
     * @param clientAppRegistrationInfo 客户端应用注册信息
     * @return
     */
    public static AuthorizeBuilder authorize(ClientAppRegistrationInfo clientAppRegistrationInfo) {
        return new AuthorizeBuilder(clientAppRegistrationInfo);
    }

    @Getter
    public static class AuthorizeBuilder implements VerifyResponseType, VerifyScope, VerifyRedirectUri, ReceiveState, SendRedirectI {

        private String clientId;
        private String scope;
        private String state;
        private String redirectUri;

        private String responseQueryParam;

        public AuthorizeBuilder(ClientAppRegistrationInfo clientAppRegistrationInfo) {
            if (clientAppRegistrationInfo == null) {
                throw new NotFound404Exception();
            }

            this.clientId = clientAppRegistrationInfo.getClientId();
            this.scope = clientAppRegistrationInfo.getScope();
            this.redirectUri = clientAppRegistrationInfo.getRedirectUri();

        }

        /**
         * 验证 response_type
         *
         * @param responseType
         * @return
         */
        @Override
        public VerifyScope verifyResponseType(String responseType) {
            log.debug("authorize verifyResponseType...");
            boolean checkResponseType = ResponseType.Code.equals(ResponseType.of(responseType));
            if (!checkResponseType) {
                String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
                String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage()
                    + "- 参数[response_type]";
                throw new OAuthException(this.redirectUri, OAuthResponse.buildFailure(errCode, errMessage).toQuery());
            }
            return this;
        }

        @Override
        public VerifyRedirectUri verifyScope(String scope) {
            log.debug("authorize verifyScope...");
            String errCode = ErrCode.UserErr.UserReqParamErr.A0410.name();
            String errMessage = ErrCode.UserErr.UserReqParamErr.A0410.getErrMessage()
                + " - 参数[scope]";
            Assert.notNull(scope, errCode, errMessage);
            if (!this.scope.equals(scope)) {
                errCode = ErrCode.UserErr.UserReqParamErr.A0420.name();
                errMessage = ErrCode.UserErr.UserReqParamErr.A0420.getErrMessage()
                    + " - 参数 [scope]";
                throw new OAuthException(this.redirectUri, OAuthResponse.buildFailure(errCode, errMessage).toQuery());
            }
            responseQueryParam += OAuthResponse.buildFailure(errCode, errMessage);
            return this;
        }

        @Override
        public ReceiveState verifyRedirectUri(String redirectUri) {
            String errCode = ErrCode.UserErr.UserReqParamErr.A0410.name();
            String errMessage = ErrCode.UserErr.UserReqParamErr.A0410.getErrMessage();
            Assert.notNull(this.redirectUri, errCode, errMessage + " - 参数[redirect_rui] 应用注册信息中未注册[redirect_rui]");
            Assert.notNull(redirectUri, errCode, errMessage + " - 参数 [redirect_uri]");

            if (!redirectUri.equals(this.redirectUri)) {
                errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
                errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage() +
                    " - 参数 [redirect_uri]:与注册值不匹配";
                throw new OAuthException(this.redirectUri, OAuthResponse.buildFailure(errCode, errMessage).toQuery());
            }
            return this;
        }

        @Override
        public SendRedirectI receiveState(String state) {
            this.state = state;
            return this;
        }

        public String sendRedirect() {
            // 生成code
            String code = UUID.randomUUID().toString();
            // 缓存
            authorizationCodeCacheMap.put(code, this.clientId + this.redirectUri);
            String redirectUri = String.format("%s?code=%s", this.redirectUri, code);
            if (state != null) {
                redirectUri = String.format("%s&state=%s", redirectUri, state);
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

            return this.accessTokenResponse;
        }
    }


}
