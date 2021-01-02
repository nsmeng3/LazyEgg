package io.lazyegg.auth.domain.oauth;

/**
 * 设置跳转地址
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 8:13 下午
 */
public interface VerifyRedirectUri {

    /**
     * 设置跳转地址
     *
     * @param redirectUri
     * @return
     */
    ReceiveState verifyRedirectUri(String redirectUri);
}
