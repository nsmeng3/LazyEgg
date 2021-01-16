package io.lazyegg.amis.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * App多页面应用
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 8:48 下午
 * @link https://baidu.gitee.io/amis/zh-CN/docs/components/app
 */
@Slf4j
@Getter
@Setter
public class App implements SchemaNode {

    private final String type = "app";
    private String brandName;

    /**
     * 支持图片地址，或者 svg。
     */
    private String logo;

    /**
     * css 类名。
     */
    private String className;

    /**
     * 顶部区域
     */
    private SchemaNode header;

    /**
     * 页面菜单上前面区域。
     */
    private SchemaNode asideBefore;
    /**
     * 页面菜单下前面区域。
     */
    private SchemaNode asideAfter;

    /**
     * 页面。
     */
    private SchemaNode footer;

    private ArrayList<SchemaNode> pages;




}
