package io.lazyegg.exception;

import com.alibaba.cola.exception.BaseException;
import io.lazyegg.constants.RequestParamType;
import io.lazyegg.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * 未登录异常捕获
 * 用于跳转到登录页
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/2 12:01 下午
 */
@Slf4j
public class NotLoggedInException extends BaseException {

    private static final String DEFAULT_ERR_CODE = "NOT_LOGGED_IN";

    private final HttpServletRequest request;

    public NotLoggedInException(HttpServletRequest request) {
        super(DEFAULT_ERR_CODE);
        this.request = request;
    }

    public String loginUrl() {
        String contextPath = request.getContextPath();
        String loginUrl = contextPath + "/login";
        HashMap<String, Object> params = ServletUtils.requestParams(request, RequestParamType.Query);
        String clientId = MapUtils.getString(params, "client_id");
        if (StringUtils.isNotBlank(clientId)) {
            loginUrl += String.format("?client_id=%s", clientId);
        }
        String returnTo = null;
        try {
            String queryString = request.getQueryString();
            if (StringUtils.isBlank(queryString)) {
                returnTo = URLEncoder.encode(request.getRequestURI(), "UTF-8");
            } else {
                returnTo = URLEncoder.encode(String.format("%s?%s", request.getRequestURI(), queryString), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        if (loginUrl.contains("?")) {
            loginUrl += String.format("return_to=%s", returnTo);
        } else {
            loginUrl += String.format("?return_to=%s", returnTo);
        }
        return loginUrl;
    }
}
