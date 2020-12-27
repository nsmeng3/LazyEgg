package io.lazyegg.exception;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import io.lazyegg.util.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 10:09 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Response handleBizException(BaseException e){
        return Response.buildFailure(e.getErrCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response handleBizException(Exception e){
        if ("org.apache.shiro.authz.UnauthorizedException".equals(e.getClass().getName())) {
            log.warn("无权访问:{}",e.getCause().getMessage());
            return Response.buildFailure(String.valueOf(HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED.name());
        }
        return Response.buildFailure("Unknown", e.getMessage());
    }
}
