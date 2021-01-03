package io.lazyegg.web;

import com.alibaba.fastjson.JSONObject;
import io.lazyegg.constants.RequestParamType;
import io.lazyegg.exception.NotFound404Exception;
import io.lazyegg.exception.NotLoggedInException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/23 12:24 上午
 */
@Slf4j
@Controller
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    /**
     * 获取全部request 参数
     * 包含header query body
     *
     * @return
     */
    public JSONObject requestParams() {
        return requestParams(request);
    }

    public JSONObject requestParams(HttpServletRequest request) {
        return requestParams(request, RequestParamType.AllParam);
    }

    public JSONObject requestParams(RequestParamType type) {
        return requestParams(request, type);
    }

    /**
     * 获取request参数 - 指定请求参数
     *
     * @param request
     * @param type
     * @return
     */
    public JSONObject requestParams(HttpServletRequest request, RequestParamType type) {
        return choose(request, type);
    }

    /**
     * 获取request参数 - 指定返回类型
     *
     * @param clientObject
     * @param <T>
     * @return
     */
    public <T> T requestParams(Class<T> clientObject) {
        return requestParams(request, clientObject);
    }

    public <T> T requestParams(HttpServletRequest request, Class<T> clientObject) {
        return requestParams(request, clientObject, RequestParamType.AllParam);
    }

    public <T> T requestParams(Class<T> clientObject, RequestParamType type) {
        return requestParams(request, clientObject, type);
    }

    public <T> T requestParams(HttpServletRequest request, Class<T> clientObject, RequestParamType type) {
        JSONObject result = requestParams(request, type);
        return JSONObject.toJavaObject(result, clientObject);
    }

    /**
     * 指定获取参数
     *
     * @param request
     * @param type
     * @return
     */
    private JSONObject choose(HttpServletRequest request, RequestParamType type) {
        JSONObject result = new JSONObject();
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
    private JSONObject getHeaderParam(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        return whileHeaderEnumeration(request, headerNames);
    }

    /**
     * 获取query参数
     *
     * @return
     */
    private JSONObject getQueryParam(HttpServletRequest request) {
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
    private JSONObject whileParameterEnumeration(HttpServletRequest request, Enumeration<String> paramNames) {
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
    private JSONObject whileHeaderEnumeration(HttpServletRequest request, Enumeration<String> paramNames) {
        JSONObject result = new JSONObject();
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
    private JSONObject getBodyParam(HttpServletRequest request) {
        // 获取 body参数
        JSONObject result = new JSONObject();
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

    public HttpServletRequest getRequest() {
        return request;
    }

}
