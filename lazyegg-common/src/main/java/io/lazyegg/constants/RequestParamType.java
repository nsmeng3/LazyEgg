package io.lazyegg.constants;

/**
 * 请求参数类型
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/23 10:57 下午
 */
public enum RequestParamType {
    Header("Header", "请求头参数"),
    Query("Query", "?后跟随的参数,form表单提交的参数"),
    Body("Body", "请求体参数,仅支持json格式"),
    QueryBody("QueryBody", "query和body参数"),
    HeaderBody("HeaderBody", "Header和Body参数"),
    AllParam("AllParam", "全部类型的请求参数")
    ;

    private final String code;
    private final String desc;

    RequestParamType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
