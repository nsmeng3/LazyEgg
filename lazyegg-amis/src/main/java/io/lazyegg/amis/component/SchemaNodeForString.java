package io.lazyegg.amis.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/17 1:18 上午
 */

@Slf4j
@Getter
@Setter
public class SchemaNodeForString implements Schema{

    private String value;

    public SchemaNodeForString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }


}
