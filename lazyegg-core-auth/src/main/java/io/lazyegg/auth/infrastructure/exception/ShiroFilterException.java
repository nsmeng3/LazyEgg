package io.lazyegg.auth.infrastructure.exception;

import com.alibaba.cola.exception.BaseException;

/**
 * Shior过滤器异常
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/23 12:04 上午
 */
public class ShiroFilterException extends BaseException {

    public ShiroFilterException(String errMessage) {
        super(errMessage);
    }

    public ShiroFilterException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public ShiroFilterException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public ShiroFilterException(String errCode, String errMessage, Throwable e) {
        super(errCode, errMessage, e);
    }
}
