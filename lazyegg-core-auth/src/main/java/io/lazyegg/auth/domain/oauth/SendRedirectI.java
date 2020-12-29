package io.lazyegg.auth.domain.oauth;

/**
 * 重定向
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 8:15 下午
 */
public interface SendRedirectI {

    /**
     * 重定向目标地址
     * @return
     */
    String sendRedirect();
}
