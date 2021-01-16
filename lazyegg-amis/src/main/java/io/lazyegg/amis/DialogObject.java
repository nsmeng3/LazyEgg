package io.lazyegg.amis;

import io.lazyegg.amis.component.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/17 4:18 上午
 */

@Slf4j
@Getter
@Setter
public class DialogObject implements Schema {
    @Override
    public String getType() {
        return "dialog";
    }

    /**
     * "dialog" 指定为 Dialog 渲染器
     */
    private String type;
    /**
     * 弹出层标题
     */
    private String title;
    /**
     * 往 Dialog 内容区加内容
     */
    private Schema body;
    /**
     * 指定 dialog 大小，支持: xs、sm、md、lg
     */
    private String size;
    /**
     * modal-body	Dialog body 区域的样式类名
     */
    private String bodyClassName;
    /**
     * false	是否支持按 Esc 关闭 Dialog
     */
    private Boolean closeOnEsc;
    /**
     * true	是否显示右上角的关闭按钮
     */
    private Boolean showCloseButton;
    /**
     * true	是否在弹框左下角显示报错信息
     */
    private Boolean showErrorMsg;
    /**
     * false	如果设置此属性，则该 Dialog 只读没有提交操作。
     */
    private Boolean disabled;
    /**
     * <Action>	【确认】和【取消】	如果想不显示底部按钮，可以配置：[]
     */
    private ArrayList<DialogAction> actions;
    /**
     * 支持数据映射，如果不设定将默认将触发按钮的上下文中继承数据。
     */
    private Object data;
}
