package io.lazyegg.auth.client.dto;

import lombok.Data;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/28 11:24 下午
 */
@Data
public class AuthorizationCodeGetCmd {

    /**
     * 响应类型 code
     */
    private String responseType;

    /**
     * 客户端应用id
     */
    private String clientId;

    /**
     * 跳转地址
     */
    private String redirectUri;

    /**
     * 权限范围
     */
    private String scope;

    /**
     * 随机数，防止CSRF攻击
     */
    private String state;
}
