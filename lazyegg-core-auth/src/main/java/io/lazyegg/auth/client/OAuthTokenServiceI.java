package io.lazyegg.auth.client;

import com.alibaba.cola.dto.Response;
import io.lazyegg.auth.client.dto.AuthorizationCodeGetCmd;
import io.lazyegg.auth.client.dto.OAuthTokenGetCmd;

/**
 * OAuthTokenServiceI
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/31 1:21 上午
 */
public interface OAuthTokenServiceI {
    Response accessToken(OAuthTokenGetCmd cmd);

    String authorizeRedirect(AuthorizationCodeGetCmd cmd);
}
