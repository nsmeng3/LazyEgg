package io.lazyegg.exception;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import io.lazyegg.constants.ErrCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

/**
 * 全局异常处理
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 10:09 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Response handleBizException(BaseException e) {
        return Response.buildFailure(e.getErrCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response handleBizException(Exception e) throws ClassNotFoundException {
        if ("org.apache.shiro.authz.UnauthorizedException".equals(e.getClass().getName())) {
            log.warn("没有访问权限 --->> {}", e.getCause().getMessage());
            return Response.buildFailure(ErrCode.UserErr.AssessPermissionException.A0301.name(),
                ErrCode.UserErr.AssessPermissionException.A0301.getErrMessage());
        } else if ("org.springframework.web.servlet.NoHandlerFoundException".equals(e.getClass().getName())) {
            return Response.buildFailure(ErrCode.NOT_FOUND.name(), ErrCode.NOT_FOUND.getErrMessage());
        }else if (e instanceof ServletException) {
            return Response.buildFailure("HTTP_ERR", e.getMessage());
        }
        log.error(e.getMessage(), e);
        return Response.buildFailure(ErrCode.UNKNOWN_ERR.name(), ErrCode.UNKNOWN_ERR.getErrMessage());
    }
}
