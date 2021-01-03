package io.lazyegg.exception;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import com.alibaba.fastjson.JSONObject;
import io.lazyegg.constants.ErrCode;
import io.lazyegg.util.HttpContextUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器全局异常处理
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/22 10:17 下午
 */
@Slf4j
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {

    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        Response res = exceptionHandler(ex, response);
        response.getWriter().print(JSONObject.toJSONString(res));
        return null;
    }

    @SneakyThrows
    private Response exceptionHandler(Throwable e, HttpServletResponse response) {
        if (e.getCause() instanceof NotFound404Exception) {
            response.setStatus(404);
            return null;
        } else if (e.getCause() instanceof NotLoggedInException) {
            String loginUrl = ((NotLoggedInException) e.getCause()).loginUrl();
            response.sendRedirect(loginUrl);
            return null;
        } else if (e.getCause() instanceof BaseException) {
            BaseException baseException = (BaseException) e.getCause();
            log.error("{}-{}", baseException.getErrCode(), baseException.getMessage());
            return Response.buildFailure(baseException.getErrCode(), baseException.getMessage());
        } else {
            log.error(e.getMessage(), e);
            return Response.buildFailure(ErrCode.SysExecErr.B0001.name(), ErrCode.SysExecErr.B0001.getErrMessage());
        }
    }


//    @Bean
//    @Primary
//    public WebMvcProperties webMvcProperties() {
//        WebMvcProperties webMvcProperties = new WebMvcProperties();
//        webMvcProperties.setThrowExceptionIfNoHandlerFound(true);
//        return webMvcProperties;
//    }
//
//    @Bean
//    @Primary
//    public ResourceProperties resourceProperties() {
//        ResourceProperties webMvcProperties = new ResourceProperties();
//        webMvcProperties.setAddMappings(false);
//        return webMvcProperties;
//    }


}
