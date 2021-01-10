package io.lazyegg.web;

import com.alibaba.fastjson.JSONObject;
import io.lazyegg.constants.RequestParamType;
import io.lazyegg.exception.NotFound404Exception;
import io.lazyegg.exception.NotLoggedInException;
import io.lazyegg.util.ServletUtils;
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
import java.util.HashMap;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/23 12:24 上午
 */
@Slf4j
@Controller
public abstract class BaseController extends ServletUtils {

    @Resource
    private HttpServletRequest request;

    /**
     * 获取全部request 参数
     * 包含header query body
     *
     * @return
     */
    public HashMap<String, Object> requestParams() {
        return ServletUtils.requestParams(request);
    }

    public HashMap<String, Object> requestParams(RequestParamType type) {
        return ServletUtils.requestParams(request, type);
    }

    /**
     * 获取request参数 - 指定返回类型
     *
     * @param clientObject
     * @param <T>
     * @return
     */
    public <T> T requestParams(Class<T> clientObject) {
        return ServletUtils.requestParams(request, clientObject);
    }


    public <T> T requestParams(Class<T> clientObject, RequestParamType type) {
        return ServletUtils.requestParams(request, clientObject, type);
    }


    public HttpServletRequest getRequest() {
        return request;
    }


}
