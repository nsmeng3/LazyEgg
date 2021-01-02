package io.lazyegg.auth.domain.oauth;

import lombok.Data;

/**
 * 授权请求
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/31 1:06 上午
 */
@Data
public class AuthorizationRequest {
    private String responseType;
    private String clientId;
    private String scope;
    private String state;
    private String redirectUri;

    public AuthorizationRequest(String clientId, String redirectUri) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
    }
}
