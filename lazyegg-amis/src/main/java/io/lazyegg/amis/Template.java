package io.lazyegg.amis;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 模板
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:55 下午
 */

@Slf4j
@Getter
@Setter
public class Template {

    private String value;

    private Boolean show;

    // TODO 验证
    public Template(String value) {
        this.value = value;
    }

}
