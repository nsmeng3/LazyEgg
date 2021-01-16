package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 是否是合法的 Json 字符串。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:22 上午
 */

@Slf4j
@Getter
@Setter
public class IsJson extends AbsTrueFalseValid{
    @Override
    public String validationItem() {
        return "isJson";
    }
}
