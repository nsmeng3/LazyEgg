package io.lazyegg.auth.domain.oauth;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/31 12:25 上午
 */
public interface ExpiredCode {

    IssueAccessToken expiredCode();
}
