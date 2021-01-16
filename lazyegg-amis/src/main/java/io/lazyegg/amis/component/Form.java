package io.lazyegg.amis.component;

import io.lazyegg.amis.API;
import io.lazyegg.amis.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Form表单
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:46 下午
 */

@Slf4j
@Getter
@Setter
public class Form implements SchemaNode {

    /**
     * "form" 指定为 Form 渲染器
     */
    private String type;
    /**
     * 设置一个名字后，方便其他组件与其通信
     */
    private String name;
    /**
     * normal
     * 表单展示方式，可以是：normal、horizontal 或者 inline
     */
    private String mode;
    /**
     * {"left":"col-sm-2", "right":"col-sm-10", "offset":"col-sm-offset-2"}
     * 当 mode 为 horizontal 时有用，用来控制 label
     */
    private Object horizontal = "{\"left\":\"col-sm-2\", \"right\":\"col-sm-10\", \"offset\":\"col-sm-offset-2\"}";
    /**
     * "表单" Form 的标题
     */
    private String title;
    /**
     * "提交"	默认的提交按钮名称，如果设置成空，则可以把默认按钮去掉。
     */
    private String submitText;
    /**
     * 外层 Dom 的类名
     */
    private String className;
    /**
     * <表单项>		Form 表单项集合
     */
    private ArrayList<Control> controls;
    /**
     * <行为按钮>		Form 提交按钮，成员为 Action
     */
    private ArrayList<Action> actions;
    /**
     * 消息提示覆写，默认消息读取的是 API 返回的消息，但是在此可以覆写它。
     */
    private Message messages;

    /**
     * true	是否让 Form 用 panel 包起来，设置为 false 后，actions 将无效。
     */
    private boolean wrapWithPanel;
    /**
     * 外层 panel 的类名
     */
    private String panelClassName;
    /**
     * Form 用来保存数据的 api。
     */
    private API api;
    /**
     * Form 用来获取初始数据的 api。
     */
    private API initApi;
    /**
     * 3000	刷新时间(最低 3000)
     */
    private Integer interval;
    /**
     * false	配置刷新时是否显示加载动画
     */
    private boolean silentPolling;
    /**
     * ""	通过表达式 来配置停止刷新的条件
     */
    private String stopAutoRefreshWhen;
    /**
     * Form 用来获取初始数据的 api,与 initApi 不同的是，会一直轮询请求该接口，直到返回 finished 属性为 true 才 结束。
     */
    private API initAsyncApi;
    /**
     * true	设置了 initApi 或者 initAsyncApi 后，默认会开始就发请求，设置为 false 后就不会起始就请求接口
     */
    private boolean initFetch;
    /**
     * 用表达式来配置
     */
    private String initFetchOn;
    /**
     * finished	设置了 initAsyncApi 后，默认会从返回数据的 data.finished 来判断是否完成，也可以设置成其他的 xxx，就会从 data.xxx 中获取
     */
    private String initFinishedField;
    /**
     * 3000	设置了 initAsyncApi 以后，默认拉取的时间间隔
     */
    private Integer initCheckInterval;
    /**
     * 设置此属性后，表单提交发送保存接口后，还会继续轮询请求该接口，直到返回 finished 属性为 true 才 结束。
     */
    private API asyncApi;
    /**
     * 3000	轮询请求的时间间隔，默认为 3 秒。设置 asyncApi 才有效
     */
    private Integer checkInterval;
    /**
     * "finished"	如果决定结束的字段名不是 finished 请设置此属性，比如 is_success
     */
    private String finishedField;
    /**
     * false	表单修改即提交
     */
    private boolean submitOnChange;
    /**
     * false	初始就提交一次
     */
    private boolean submitOnInit;
    /**
     * false	提交后是否重置表单
     */
    private boolean resetAfterSubmit;
    /**
     * "id"	设置主键 id, 当设置后，检测表单是否完成时（asyncApi），只会携带此数据。
     */
    private String primaryField;
    /**
     * 默认表单提交自己会通过发送 api 保存数据，但是也可以设定另外一个 form 的 name 值，或者另外一个 CRUD 模型的 name 值。 如果 target 目标是一个 Form ，则目标 Form 会重新触发 initApi，api 可以拿到当前 form 数据。如果目标是一个 CRUD 模型，则目标模型会重新触发搜索，参数为当前 Form 数据。当目标是 window 时，会把当前表单的数据附带到页面地址上。
     */
    private String target;
    /**
     * 设置此属性后，Form 保存成功后，自动跳转到指定页面。支持相对地址，和绝对地址（相对于组内的）。
     */
    private String redirect;
    /**
     * 操作完后刷新目标对象。请填写目标组件设置的 name 值，如果填写为 window 则让当前页面整体刷新。
     */
    private String reload;
    /**
     * false	是否自动聚焦。
     */
    private boolean autoFocus;
    /**
     * true	指定是否可以自动获取上层的数据并映射到表单项上
     */
    private boolean canAccessSuperData;
    /**
     * true	指定表单是否开启本地缓存
     */
    private boolean persistData;
    /**
     * true	指定表单提交成功后是否清除本地缓存
     */
    private boolean clearPersistDataAfterSubmit;
    /**
     * false	trim 当前表单项的每一个值
     */
    private boolean trimValues;
    /**
     * false	form 还没保存，即将离开页面前是否弹框确认。
     */
    private boolean promptPageLeave;
}
