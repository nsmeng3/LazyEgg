package io.lazyegg.amis.valid;

/**
 * 范围校验
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 10:49 下午
 */
public abstract class AbsRangeValid implements ValidationItem<Integer> {

    protected Integer rangeValue;


    public AbsRangeValid(Integer rangeValue) {
        this.rangeValue = rangeValue;
    }


    @Override
    public Integer getValidParam() {
        return rangeValue;
    }
}
