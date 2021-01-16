package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 必须命中某个正则。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:36 上午
 */

@Slf4j
@Getter
@Setter
public class MatchRegex extends AbsMatchRegexpValid{
    public MatchRegex(String regxStr) {
        super(regxStr);
    }

    @Override
    public String validationItem() {
        return "matchRegexp";
    }
}
