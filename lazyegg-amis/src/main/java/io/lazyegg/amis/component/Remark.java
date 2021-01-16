package io.lazyegg.amis.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Remark 标记
 * 用于展示提示文本，和表单项中的 remark 属性类型。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:02 下午
 */
@Slf4j
@Getter
@Setter
public class Remark implements SchemaNode {

    /**
     * remark
     */
    private String type;
    /**
     * 外层 CSS 类名
     */
    private String className;
    /**
     * 提示文本
     */
    private String content;
    /**
     * 弹出位置
     */
    private String placement;
    /**
     * ['hover', 'focus']	触发条件
     */
    private String trigger;
    /**
     * fa fa-question-circle	图标
     */
    private String icon;
}
