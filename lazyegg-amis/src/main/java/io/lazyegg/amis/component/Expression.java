package io.lazyegg.amis.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 一般来说，属性名类似于xxxOn的配置项，都可以使用表达式进行配置，表达式具有如下的语法：
 * {
 *      "type": "tpl",
 *      "tpl": "当前作用域中变量 show 是 1 的时候才可以看得到我哦~",
 *      "visibleOn": "this.show === 1"
 * }
 *
 * 其中：this.show === 1 就是表达式。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:14 下午
 * @see https://baidu.gitee.io/amis/zh-CN/docs/concepts/expression
 */

@Slf4j
@Getter
@Setter
public class Expression {

    // TODO 表达式逻辑验证
    private String value;
}
