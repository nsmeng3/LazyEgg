package io.lazyegg.auth.infrastructure.exception;

import io.lazyegg.exception.NotLoggedInException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常处理
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/2 12:37 上午
 */
@Slf4j
@RestControllerAdvice
public class OAuthExceptionHandler {

    @Resource
    private HttpServletResponse response;

    /**
     * 授权异常处理
     *
     * @param e
     */
    @ExceptionHandler(OAuthException.class)
    public void handleOAuthException(OAuthException e) throws IOException {
        log.info("授权异常：to login page ...{}", e.getErrRedirect());
        response.sendRedirect(e.getErrRedirect());
    }

    /**
     * 未登录异常处理
     * <p>
     * 跳转登录页
     *
     * @param e
     */
    @ExceptionHandler(NotLoggedInException.class)
    public void handleNotLoggedInException(NotLoggedInException e) throws IOException {
        log.info("to login page ...{}", e.loginUrl());
        response.sendRedirect(e.loginUrl());
    }
}
