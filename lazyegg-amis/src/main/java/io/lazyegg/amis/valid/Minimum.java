package io.lazyegg.amis.valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 最小值
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:18 上午
 */

@Slf4j
@Getter
@Setter
public class Minimum extends AbsRangeValid{
    public Minimum(Integer rangeValue) {
        super(rangeValue);
    }

    @Override
    public String validationItem() {
        return "minimum";
    }

}
