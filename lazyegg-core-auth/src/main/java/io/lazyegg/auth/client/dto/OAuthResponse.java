package io.lazyegg.auth.client.dto;

import com.alibaba.cola.exception.SysException;
import io.lazyegg.util.LineTurnsToHumpUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Query响应
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/1 10:16 下午
 */
@Slf4j
@Getter
@Setter
public class OAuthResponse<T> {

    private String errCode;

    private String errDesc;

    private T data;

    public static <T> OAuthResponse<T> buildFailure(String errCode, String errDesc) {
        OAuthResponse<T> response = new OAuthResponse<>();
        response.setErrCode(errCode);
        response.setErrDesc(errDesc);
        return response;
    }

    public static <T> OAuthResponse<T> of(T data) {
        OAuthResponse<T> response = new OAuthResponse<>();
        response.setData(data);
        return response;
    }

    public String toQuery() {
        return toQuery(true, true);
    }

    public String toQuery(boolean enableTurn, boolean showNullField) {
        return "?" + toQueryParamStr(enableTurn, showNullField);
    }

    public String toQuery(boolean enableTurn) {
        return toQuery(enableTurn, true);
    }


    /**
     * 转query类型格式
     * <p>
     * 默认开启下划线驼峰互转、默认输出空字段
     *
     * @return
     */
    public String toQueryParamStr() {
        return toQueryParamStr(true, true);
    }

    /**
     * 转query类型格式
     * <p>
     * 默认输出空字段
     *
     * @param enableTurn 开启下划线驼峰互转
     * @return
     */
    public String toQueryParamStr(boolean enableTurn) {
        return toQueryParamStr(enableTurn, true);
    }

    /**
     * 转query类型格式
     *
     * @param enableTurn    开启下划线驼峰互转
     * @param showNullField 展示空字段
     * @return
     */
    public String toQueryParamStr(boolean enableTurn, boolean showNullField) {
        StringBuilder queryStr = new StringBuilder();
        if (data == null) {
            try {
                queryStr.append(String.format("%s=%s", "errCode", URLEncoder.encode(this.errCode, "UTF-8"))).append("&");
                queryStr.append(String.format("%s=%s", "errDesc", URLEncoder.encode(this.errDesc, "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                throw new SysException(e.getMessage());
            }
            return queryStr.toString();
        }
        if (data instanceof Map) {
            Map<Object, Object> data = (Map<Object, Object>) this.data;
            for (Object key : data.keySet()) {
                String name = key.toString();
                Object obj = data.get(key);
                // 不输出空字段
                if (!showNullField) {
                    continue;
                }
                String value = String.valueOf(obj);
                // 开启互转
                if (enableTurn) {
                    name = LineTurnsToHumpUtils.turns(name);
                }
                try {
                    name = URLEncoder.encode(name, "UTF-8");
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new SysException(e.getMessage());
                }
                queryStr.append(String.format("%s=%s", name, value)).append("&");
            }
            return queryStr.substring(0, queryStr.length() - 1);
        }

        Class<T> aClass = (Class<T>) data.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object obj = field.get(data);
                // 不输出空字段
                if (!showNullField) {
                    continue;
                }
                String value = String.valueOf(obj);
                if (enableTurn) {
                    name = LineTurnsToHumpUtils.turns(name);
                }
                try {
                    name = URLEncoder.encode(name, "UTF-8");
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new SysException(e.getMessage());
                }
                log.info("{}={}", name, value);
                queryStr.append(String.format("%s=%s", name, value)).append("&");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return queryStr.substring(0, queryStr.length() - 1);


    }

    @Override
    public String toString() {
        return toQueryParamStr(true, true);
    }
}
