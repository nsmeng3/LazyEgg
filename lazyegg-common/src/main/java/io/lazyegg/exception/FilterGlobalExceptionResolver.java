package io.lazyegg.exception;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import com.alibaba.fastjson.JSONObject;
import io.lazyegg.constants.ErrCode;
import io.lazyegg.util.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器全局异常处理
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/22 10:17 下午
 */
@Slf4j
@Component
public class FilterGlobalExceptionResolver {
    @Bean
    public Filter filter() {
        return (request, response, chain) -> {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            try {
                chain.doFilter(request, response);
            } catch (Exception e) {
                Response res;
                if (e.getCause() instanceof BaseException) {
                    BaseException baseException = (BaseException) e.getCause();
                    log.error("{}-{}",baseException.getErrCode(),baseException.getMessage());
                    res = Response.buildFailure(baseException.getErrCode(), baseException.getMessage());
                } else {
                    log.error(e.getMessage(), e);
                    res = Response.buildFailure(ErrCode.SysExecErr.B0001.name(), ErrCode.SysExecErr.B0001.getErrMessage());
                }
                response.getWriter().print(JSONObject.toJSONString(res));
            }
        };
    }

    @Bean
    @Primary
    public WebMvcProperties webMvcProperties() {
        WebMvcProperties webMvcProperties = new WebMvcProperties();
        webMvcProperties.setThrowExceptionIfNoHandlerFound(true);
        return webMvcProperties;
    }

    @Bean
    @Primary
    public ResourceProperties resourceProperties() {
        ResourceProperties webMvcProperties = new ResourceProperties();
        webMvcProperties.setAddMappings(false);
        return webMvcProperties;
    }


}
