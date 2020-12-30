package io.lazyegg.auth.client.dto;

import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/29 12:18 上午
 */
@Data
public class OAuthTokenGetCmd {
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String code;
    private String redirectUri;
}
