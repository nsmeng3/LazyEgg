package io.lazyegg.amis.component;

import io.lazyegg.amis.API;
import io.lazyegg.amis.exception.AmisException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Page 页面
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 8:48 下午
 * @link https://baidu.gitee.io/amis/zh-CN/docs/components/page
 */

@Slf4j
@Getter
@Setter
@Data
public class Page<T> implements SchemaNode{

    public Page(Schema body) {
        setBody((T) body);
    }

    public Page(ArrayList<Schema> body) {
        setBody((T) body);
    }

    public Page(String body) {
       setBody((T) body);
    }

    private void setBody(T body) {
        this.body = body;
    }

    private final String type = "page";

    /**
     * 页面标题
     */
    private T title;
    /**
     * 页面副标题
     */
    private T subTitle;
    /**
     * 标题附近会出现一个提示图标，鼠标放上去会提示该内容。
     */
    private Remark remark;
    /**
     * 往页面的边栏区域加内容
     */
    private T aside;
    /**
     * 往页面的右上角加内容，需要注意的是，当有 title 时，该区域在右上角，没有时该区域在顶部
     */
    private T toolbar;
    /**
     * 往页面的内容区域加内容
     */
    private T body;
    /**
     * 外层 dom 类名
     */
    private String className;
    /**
     * 自定义 CSS 变量，请参考样式
     */
    // TODO CSS变量类型有待确定
    private Object cssVars;
    /**
     * v-middle wrapper text-right bg-light b-b	Toolbar dom 类名
     */
    private String toolbarClassName;
    /**
     * wrapper	Body dom 类名
     */
    private String bodyClassName;
    /**
     * w page-aside-region bg-auto	Aside dom 类名
     */
    private String asideClassName;
    /**
     * bg-light b-b wrapper	Header 区域 dom 类名
     */
    private String headerClassName;
    /**
     * Page 用来获取初始数据的 api。返回的数据可以整个 page 级别使用。
     */
    private API initApi;
    /**
     * true	是否起始拉取 initApi
     */
    private Boolean initFetch;
    /**
     * 是否起始拉取 initApi, 通过表达式配置
     */
    private Expression initFetchOn;
    /**
     * 3000	刷新时间(最低 3000)
     */
    private Integer interval;
    /**
     * false	配置刷新时是否显示加载动画
     */
    private Boolean silentPolling;
    /**
     * 通过表达式来配置停止刷新的条件
     */
    private Expression stopAutoRefreshWhen;

    public void setInterval(Integer interval) throws AmisException {
        if (interval <= 3000) {
            throw new AmisException("刷新时间(最低 3000)");
        }
        this.interval = interval;
    }


}
