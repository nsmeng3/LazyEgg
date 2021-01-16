package io.lazyegg.amis.component;

import io.lazyegg.amis.Template;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:57 下午
 */

@Slf4j
@Getter
@Setter
public class Action implements SchemaNode {

    /**
     * action	指定为 Page 渲染器。
     */
    private final String type = "action";
    /**
     * -	【必填】这是 action 最核心的配置，来指定该 action 的作用类型，支持：ajax、link、url、drawer、dialog、confirm、cancel、prev、next、copy、close。
     */
    private String actionType;
    /**
     * -	按钮文本。可用 ${xxx} 取值。
     */
    private String label;
    /**
     * default	按钮样式，支持：link、primary、secondary、info、success、warning、danger、light、dark、default。
     */
    private String level;
    /**
     * -	按钮大小，支持：xs、sm、md、lg。
     */
    private String size;
    /**
     * -	设置图标，例如fa fa-plus。
     */
    private String icon;
    /**
     * -	给图标上添加类名。
     */
    private String iconClassName;
    /**
     * -	按钮是否高亮。
     */
    private Boolean active;
    /**
     * -	按钮高亮时的样式，配置支持同level。
     */
    private String activeLevel;
    /**
     * is-active	给按钮高亮添加类名。
     */
    private String activeClassName;
    /**
     * -	用display:"block"来显示按钮。
     */
    private Boolean block;
    /**
     * -	当设置后，操作在开始前会询问用户。可用 ${xxx} 取值。
     */
    private Template confirmText;
    /**
     * -	指定此次操作完后，需要刷新的目标组件名字（组件的name值，自己配置的），多个请用 , 号隔开。
     */
    private String reload;
    /**
     * -	鼠标停留时弹出该段文字，也可以配置对象类型：字段为title和content。可用 ${xxx} 取值。
     */
    private String tooltip;
    /**
     * -	被禁用后鼠标停留时弹出该段文字，也可以配置对象类型：字段为title和content。可用 ${xxx} 取值。
     */
    private String disabledTip;
    /**
     * top	如果配置了tooltip或者disabledTip，指定提示信息位置，可配置top、bottom、left、right。
     */
    private String tooltipPlacement;
    /**
     * or String	-	当action配置在dialog或drawer的actions中时，配置为true指定此次操作完后关闭当前dialog或drawer。当值为字符串，并且是祖先层弹框的名字的时候，会把祖先弹框关闭掉。
     */
    private Boolean close;
    /**
     * <String>	-	配置字符串数组，指定在form中进行操作之前，需要指定的字段名的表单项通过验证
     */
    private ArrayList<String> required;
}
