package io.lazyegg.auth.infrastructure.exception;

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
     * 授权异常捕获
     * @param e
     */
    @ExceptionHandler(OAuthException.class)
    public void handleOAuthException(OAuthException e) {
        try {
            response.sendRedirect(e.getErrRedirect());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
