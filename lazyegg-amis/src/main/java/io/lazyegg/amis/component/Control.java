package io.lazyegg.amis.component;

import io.lazyegg.amis.Template;
import io.lazyegg.amis.valid.Validation;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 表单项 是组成一个表单的基本单位，
 * 它具有的一些特性会帮助我们更好地实现表单操作。
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:50 下午
 */

@Slf4j
@Getter
@Setter
public abstract class Control implements SchemaNode {

    /**
     * 表单最外层类名
     */
    private String className;
    /**
     * 表单控制器类名
     */
    private String inputClassName;
    /**
     * label 的类名
     */
    private String labelClassName;
    /**
     * 字段名，指定该表单项提交时的 key
     */
    private String name;
    /**
     * 或 false		表单项标签
     */
    private Template label;
    /**
     * 表单项标签描述
     */
    private Remark labelRemark;
    /**
     * 表单项描述
     */
    private Template description;
    /**
     * 表单项描述
     */
    private String placeholder;
    /**
     * 是否为 内联 模式
     */
    private Boolean inline;
    /**
     * 是否该表单项值发生变化时就提交当前表单。
     */
    private Boolean submitOnChange;
    /**
     * 当前表单项是否是禁用状态
     */
    private Boolean disabled;
    /**
     * 当前表单项是否禁用的条件
     */
    private Expression disabledOn;
    /**
     * 当前表单项是否禁用的条件
     */
    private Expression visible;
    /**
     * 当前表单项是否禁用的条件
     */
    private Expression visibleOn;
    /**
     * 是否为必填。
     */
    private Boolean required;
    /**
     * 过Expression来配置当前表单项是否为必填。
     */
    private Expression requiredOn;
    /**
     * 表单项值格式验证，支持设置多个。
     */
    private Validation validations;
}
