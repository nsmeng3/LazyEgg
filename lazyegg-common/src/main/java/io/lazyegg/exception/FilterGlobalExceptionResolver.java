package io.lazyegg.exception;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import com.alibaba.fastjson.JSONObject;
import io.lazyegg.util.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
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
                if (e instanceof BaseException) {
                    BaseException baseException = (BaseException) e;
                    res = Response.buildFailure(baseException.getErrCode(), baseException.getMessage());
                } else {
                    log.error(e.getMessage(), e);
                    res = Response.buildFailure("500", e.getCause().getMessage());
                }
                response.getWriter().print(JSONObject.toJSONString(res));
            }
        };
    }
}
