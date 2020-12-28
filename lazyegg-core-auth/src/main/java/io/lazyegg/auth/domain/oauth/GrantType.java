package io.lazyegg.auth.domain.oauth;

import com.alibaba.cola.exception.BizException;
import io.lazyegg.constants.ErrCode;

/**
 * GrantType
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 12:36 上午
 */
public enum GrantType {
    authorizationCode("authorization_code", "授权码模式(即先登录获取code,再获取token)"),
    password("password", "密码模式(将用户名,密码传过去,直接获取token)"),
    clientCredentials("client_credentials", "客户端模式(无用户,用户向客户端注册,然后客户端以自己的名义向’服务端’获取资源)"),
    implicit("implicit", "简化模式(在redirect_uri 的Hash传递token; Auth客户端运行在浏览器中,如JS,Flash)"),
    refreshToken("refresh_token", "刷新access_token");

    private final String type;

    GrantType(String type, String description) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static GrantType of(String type) {
        for (GrantType value : GrantType.values()) {
            if (value.getType().equals(type)) {
                return value;
            } else {
                String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
                String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage() + "- 错误参数[grant_type]";
                throw new BizException(errCode, errMessage);
            }
        }
        return null;
    }
}
