package io.lazyegg.amis.valid;

/**
 * 真假校验
 *
 * 扩展校验类型，继成该类实现validationItem方法即可
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 10:46 下午
 */
public abstract class AbsTrueFalseValid implements ValidationItem<Boolean> {

    @Override
    public Boolean getValidParam() {
        return true;
    }
}
