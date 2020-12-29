package io.lazyegg.auth.domain.oauth;

/**
 * 验证Scope
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 8:13 下午
 */
public interface VerifyScope {

    /**
     * 验证Scope
     *
     * @param scope
     * @return
     */
    SetRedirectUri verifyScope(String scope);
}
