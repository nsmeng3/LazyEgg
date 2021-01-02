package io.lazyegg.auth.domain.oauth;

import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/1 9:13 下午
 */
@Data
public class ClientAppRegistrationInfo {

    private String clientId;
    private String scope;
    private String redirectUri;
}
