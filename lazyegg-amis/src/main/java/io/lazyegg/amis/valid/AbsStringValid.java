package io.lazyegg.amis.valid;

/**
 * 字符串校验
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 10:49 下午
 */


public abstract class AbsStringValid implements ValidationItem<String> {

    protected String validParam;

    public AbsStringValid(String validParam) {
        this.validParam = validParam;
    }

    @Override
    public String getValidParam() {
        return validParam;
    }
}
