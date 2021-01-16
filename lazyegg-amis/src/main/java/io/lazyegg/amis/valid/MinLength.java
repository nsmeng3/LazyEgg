package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 最小长度
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:10 上午
 */

@Slf4j
@Getter
@Setter
public class MinLength extends AbsRangeValid{

    public MinLength(Integer length) {
        super(length);
    }

    @Override
    public String validationItem() {
        return "minLength";
    }

}
