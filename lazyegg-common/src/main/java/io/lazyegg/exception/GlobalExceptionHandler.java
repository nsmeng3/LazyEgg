package io.lazyegg.exception;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import io.lazyegg.constants.ErrCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/13 10:09 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler  {

    @Resource
    private HttpServletResponse response;

    @ExceptionHandler(NotFound404Exception.class)
    public void handleNotFound404Exception(NotFound404Exception e) {
        response.setStatus(404);
    }

    @ExceptionHandler(NotLoggedInException.class)
    public void handleNotLoggedInException(NotLoggedInException e) throws IOException {
        String loginUrl = e.loginUrl();
        response.sendRedirect(loginUrl);
    }

    @ExceptionHandler(BaseException.class)
    public Response handleBizException(BaseException e) {
        return Response.buildFailure(e.getErrCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response handleBizException(Exception e) {
        if ("org.apache.shiro.authz.UnauthorizedException".equals(e.getClass().getName())) {
            log.warn("没有访问权限 --->> {}", e.getCause().getMessage());
            return Response.buildFailure(ErrCode.UserErr.AssessPermissionException.A0301.name(),
                ErrCode.UserErr.AssessPermissionException.A0301.getErrMessage());
        } else if ("org.springframework.web.servlet.NoHandlerFoundException".equals(e.getClass().getName())) {
            response.setStatus(404);
            return null;
        } else if (e instanceof ServletException) {
            return Response.buildFailure("HTTP_ERR", e.getMessage());
        }
        log.error(e.getMessage(), e);
        return Response.buildFailure(ErrCode.UNKNOWN_ERR.name(), ErrCode.UNKNOWN_ERR.getErrMessage());
    }
}
