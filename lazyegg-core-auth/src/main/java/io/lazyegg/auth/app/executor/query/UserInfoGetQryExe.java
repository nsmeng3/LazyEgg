package io.lazyegg.auth.app.executor.query;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.jsonwebtoken.Claims;
import io.lazyegg.auth.client.dto.query.UserInfoGetQry;
import io.lazyegg.auth.domain.model.SysUser;
import io.lazyegg.constants.Constants;
import io.lazyegg.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取用户信息
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/10 10:26 下午
 */
@Component
public class UserInfoGetQryExe {

    @Value("${token.secret}")
    private String secret;

    public Response execute(UserInfoGetQry userInfoGetQry) {
        // 用户传来的jwt
        String authorization = userInfoGetQry.getAuthorization();
        // 解析jwt
        Claims claims = JwtUtils.parseJWT(authorization, secret);
        String token = claims.get(Constants.LOGIN_USER_KEY, String.class);
        SysUser sysUser = SysLoginByUsernamePasswordQryExe.cacheToken.get(getTokenKey(token));
        return SingleResponse.of(sysUser);
    }

    private String getTokenKey(String accessToken) {
        return Constants.LOGIN_TOKEN_KEY + accessToken;
    }
}
