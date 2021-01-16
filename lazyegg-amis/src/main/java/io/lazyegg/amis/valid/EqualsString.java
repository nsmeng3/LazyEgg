package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 当前值必须完全等于 xxx。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:08 上午
 */

@Slf4j
@Getter
@Setter
public class EqualsString extends AbsStringValid{

    public EqualsString(String validParam) {
        super(validParam);
    }

    @Override
    public String validationItem() {
        return "equals";
    }
}
