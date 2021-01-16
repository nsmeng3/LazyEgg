package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 真假校验默认校验方法
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 10:53 下午
 */

@Slf4j
@Getter
@Setter
public class IsEmptyString extends AbsTrueFalseValid {

    @Override
    public String validationItem() {
        return "isEmptyString";
    }
}
