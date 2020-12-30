package io.lazyegg.auth.domain.oauth;

import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/31 12:13 上午
 */
@Data
public class AccessTokenRequest {

    private String grantType;
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;

}
