package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 是否长度正好等于设定值。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 11:25 下午
 */

@Slf4j
@Getter
@Setter
public class IsLength extends AbsRangeValid {


    public IsLength(Integer length) {
        super(length);
    }

    @Override
    public String validationItem() {
        return "isLength";
    }


    @Override
    public Integer getValidParam() {
        return super.rangeValue;
    }
}
