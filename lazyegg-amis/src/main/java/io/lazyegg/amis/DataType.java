package io.lazyegg.amis;

/**
 * 数据体格式
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:35 下午
 */
public enum DataType {

    JSON("json"),
    FORM("form"),
    FORM_DATA("form-data");

    private String value;

    DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
