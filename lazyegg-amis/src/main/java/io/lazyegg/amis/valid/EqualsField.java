package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 当前值必须与 xxx 变量值一致。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:19 上午
 */

@Slf4j
@Getter
@Setter
public class EqualsField extends AbsStringValid{
    public EqualsField(String validParam) {
        super(validParam);
    }

    @Override
    public String validationItem() {
        return "equalsField";
    }
}
