package io.lazyegg.auth.domain.oauth;

import com.alibaba.cola.domain.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * 令牌
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 12:26 上午
 */
@Getter
@Setter
@Entity
public abstract class OAuthToken {
    private String grantType;
    private String clientId;

    public OAuthToken(String grantType, String clientId) {
        this.grantType = grantType;
        this.clientId = clientId;
    }

    /**
     * 检查 grantType
     *
     * @param grantType
     * @return
     */
    public abstract void checkGrantType(String grantType);
}
