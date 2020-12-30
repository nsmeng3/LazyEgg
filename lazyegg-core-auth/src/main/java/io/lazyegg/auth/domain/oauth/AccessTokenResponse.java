package io.lazyegg.auth.domain.oauth;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 颁发的令牌
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 1:21 上午
 */
@Data
public class AccessTokenResponse {

    /**
     * accessToken
     */
    private String accessToken;
    /**
     * tokenType
     */
    private String tokenType;
    /**
     * expiresIn
     */
    private Integer expiresIn;
    /**
     * refreshToken
     */
    private String refreshToken;
    /**
     * scope
     */
    private String scope;
    /**
     * uid
     */
    private String uid;
    /**
     * info
     */
    private Map<String, Object> info;

}
