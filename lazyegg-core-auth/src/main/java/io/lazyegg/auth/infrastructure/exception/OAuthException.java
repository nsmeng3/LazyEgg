package io.lazyegg.auth.infrastructure.exception;

import com.alibaba.cola.exception.BaseException;
import lombok.Getter;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/2 12:21 上午
 */
@Getter
public class OAuthException extends BaseException {
    private static final String DEFAULT_ERR_CODE = "OAUTH_ERROR";

    private final String errRedirect;

    public OAuthException(String errRedirect) {
        super(DEFAULT_ERR_CODE);
        this.errRedirect = errRedirect;
    }

    public OAuthException(String errRedirect, String queryParam) {
        super(DEFAULT_ERR_CODE);
        this.errRedirect = errRedirect + queryParam;
    }
}
