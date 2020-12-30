package io.lazyegg.auth.domain.oauth;

import lombok.Data;

/**
 * 授权请求
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/31 1:06 上午
 */
@Data
public class AuthorizationRequest {
    private String clientId;
    private String clientSecret;
    private String scope;
    private String state;
    private String redirectUri;
    private String responseType;
}
