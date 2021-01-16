package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 是Url路径
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:24 上午
 */

@Slf4j
@Getter
@Setter
public class IsUrlPath extends AbsTrueFalseValid {
    @Override
    public String validationItem() {
        return "isUrlPath";
    }
}
