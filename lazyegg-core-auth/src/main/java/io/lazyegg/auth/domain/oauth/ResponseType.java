package io.lazyegg.auth.domain.oauth;

import com.alibaba.cola.exception.BizException;
import io.lazyegg.constants.ErrCode;

/**
 * response_type
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/29 7:13 下午
 */
public enum ResponseType {
    Code("code", "返回授权码"),
    Token("token", "直接返回令牌");

    private final String type;

    ResponseType(String type, String desc) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ResponseType of(String type) {
        for (ResponseType value : ResponseType.values()) {
            if (value.getType().equals(type)) {
                return value;
            } else {
                String errCode = ErrCode.UserErr.UserReqParamErr.A0400.name();
                String errMessage = ErrCode.UserErr.UserReqParamErr.A0400.getErrMessage() + "- 错误参数[response_type]";
                throw new BizException(errCode, errMessage);
            }
        }
        return null;
    }
}
