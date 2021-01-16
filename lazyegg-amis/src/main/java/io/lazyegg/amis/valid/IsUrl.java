package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 必须是 Url。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 11:19 下午
 */

@Slf4j
@Getter
@Setter
public class IsUrl extends AbsTrueFalseValid{
    @Override
    public String validationItem() {
        return "isUrl";
    }
}
