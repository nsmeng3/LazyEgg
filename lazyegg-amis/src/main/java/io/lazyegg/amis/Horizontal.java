package io.lazyegg.amis;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 当 mode 为 horizontal 时有用，用来控制 label
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 3:06 上午
 */

@Slf4j
@Getter
@Setter
public class Horizontal {

    private String left = "col-sm-2";
    private String right = "col-sm-10";
    private String offset = "col-sm-offset-2";

}
