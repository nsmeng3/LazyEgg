package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 要求输入内容不是空白。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:23 上午
 */

@Slf4j
@Getter
@Setter
public class NotEmptyString extends AbsTrueFalseValid {
    @Override
    public String validationItem() {
        return "notEmptyString";
    }
}
