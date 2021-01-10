package io.lazyegg.util;

import com.alibaba.fastjson.JSONObject;
import io.lazyegg.constants.RequestParamType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Servlet 工具类
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/23 12:24 上午
 */
@Slf4j
@Component
public class ServletUtils {

    public static HttpServletRequest getRequest(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    /**
     * 获取全部request 参数
     * 包含header query body
     *
     * @return
     */

    public static HashMap<String, Object> requestParams(ServletRequest request) {
        return requestParams(getRequest(request), RequestParamType.AllParam);
    }


    /**
     * 获取request参数 - 指定请求参数
     *
     * @param request
     * @param type
     * @return
     */
    public static HashMap<String, Object> requestParams(ServletRequest request, RequestParamType type) {
        return choose(getRequest(request), type);
    }

    /**
     * 获取request参数 - 指定返回类型
     *
     * @param clientObject
     * @param <T>
     * @return
     */

    public static <T> T requestParams(ServletRequest request, Class<T> clientObject) {
        return requestParams(getRequest(request), clientObject, RequestParamType.AllParam);
    }


    public static <T> T requestParams(ServletRequest request, Class<T> clientObject, RequestParamType type) {
        HashMap<String, Object> result = requestParams(request, type);
        JSONObject jsonObject = new JSONObject(result);
        return JSONObject.toJavaObject(jsonObject, clientObject);
    }

    /**
     * 指定获取参数
     *
     * @param servletRequest
     * @param type
     * @return
     */
    public static HashMap<String, Object> choose(ServletRequest servletRequest, RequestParamType type) {
        HttpServletRequest request = getRequest(servletRequest);
        HashMap<String, Object> result = new HashMap<String, Object>();
        switch (type) {
            case Header: {
                result.putAll(getHeaderParam(request));
                return result;
            }
            case Query: {
                result.putAll(getQueryParam(request));
                return result;
            }
            case Body: {
                result.putAll(getBodyParam(request));
                return result;
            }
            case QueryBody: {
                result.putAll(getQueryParam(request));
                result.putAll(getBodyParam(request));
                return result;
            }
            case HeaderBody: {
                result.putAll(getHeaderParam(request));
                result.putAll(getBodyParam(request));
                return result;
            }
            default: {
                result.putAll(getHeaderParam(request));
                result.putAll(getQueryParam(request));
                result.putAll(getBodyParam(request));
                return result;
            }
        }
    }

    /**
     * 获取header参数
     *
     * @param request
     * @return
     */
    private static HashMap<String, Object> getHeaderParam(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        return whileHeaderEnumeration(request, headerNames);
    }

    /**
     * 获取query参数
     *
     * @return
     */
    private static JSONObject getQueryParam(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        return whileParameterEnumeration(request, parameterNames);
    }

    /**
     * 遍历 ParameterEnumeration
     *
     * @param request
     * @param paramNames
     * @return
     */
    private static JSONObject whileParameterEnumeration(HttpServletRequest request, Enumeration<String> paramNames) {
        JSONObject result = new JSONObject();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String[] parameterValues = request.getParameterValues(name);
            if (parameterValues == null) {
                continue;
            }
            String value;
            if (parameterValues.length == 1) {
                value = parameterValues[0];
                result.put(name, value);
            } else {
                result.put(name, parameterValues);
            }
        }
        return result;
    }

    /**
     * 遍历 HeaderEnumeration
     *
     * @param request
     * @param paramNames
     * @return
     */
    private static HashMap<String, Object> whileHeaderEnumeration(HttpServletRequest request, Enumeration<String> paramNames) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String parameterValues = request.getHeader(name);
            if (parameterValues == null) {
                continue;
            }
            result.put(name, parameterValues);
        }
        return result;
    }

    /**
     * 获取body参数
     *
     * @return
     */
    @SneakyThrows
    private static HashMap<String, Object> getBodyParam(HttpServletRequest request) {
        // 获取 body参数
        HashMap<String, Object> result = new HashMap<String, Object>();
        ServletInputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject != null) {
                result.putAll(jsonObject);
            }
        }
        return result;
    }


    public static String getAuthorization(ServletRequest request) {
        HashMap<String, Object> params = requestParams(request, RequestParamType.Header);
        if (params.containsKey("authorization")) {
            Object authorization = params.get("authorization");
            if (authorization == null) {
                return null;
            }
            return String.valueOf(authorization);
        } else if (params.containsKey("Authorization")) {
            Object authorization = params.get("Authorization");
            if (authorization == null) {
                return null;
            }
            return String.valueOf(authorization);
        }
        return null;
    }
}
