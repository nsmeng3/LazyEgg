package io.lazyegg.auth.domain.oauth;

/**
 * 验证 access_token 传来的 code
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/31 12:34 上午
 */
public interface VerifyCode {

    /**
     * 验证 access_token 传来的 code
     *
     * @return
     */
    GenAccessToken verifyCode();
}
