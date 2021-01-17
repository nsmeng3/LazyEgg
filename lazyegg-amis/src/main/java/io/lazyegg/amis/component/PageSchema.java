package io.lazyegg.amis.component;

import io.lazyegg.amis.API;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 页面配置
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:03 下午
 */

@Slf4j
@Getter
@Setter
public class PageSchema implements SchemaNode{

    /**
     *  菜单名称。
     */
    private String label;
    /**
     *  菜单图标，比如：fa fa-file.
     */
    private String icon;
    /**
     *  页面路由路径，当路由命中该路径时，启用当前页面。当路径不是 / 打头时，会连接父级路径。比如：父级的路径为 folder，而此时配置 pageA, 那么当页面地址为 /folder/pageA 时才会命中此页面。当路径是 / 开头如： /crud/list 时，则不会拼接父级路径。另外还支持 /crud/view/:id 这类带参数的路由，页面中可以通过 ${params.id} 取到此值。
     */
    private String url;
    /**
     *  页面的配置，具体配置请前往 Page 页面说明
     */
    private SchemaNode schema;
    /**
     *  如果想通过接口拉取，请配置。返回路径为 json>data。schema 和 schemaApi 只能二选一。
     */
    private API schemaApi;
    /**
     *  如果想配置个外部链接菜单，只需要配置 link 即可。
     */
    private String link;
    /**
     *  跳转，当命中当前页面时，跳转到目标页面。
     */
    private String redirect;
    /**
     *  改成渲染其他路径的页面，这个方式页面地址不会发生修改。
     */
    private String rewrite;
    /**
     *  当你需要自定义 404 页面的时候有用，不要出现多个这样的页面，因为只有第一个才会有用。
     */
    private String isDefaultPage;
    /**
     *  有些页面可能不想出现在菜单中，可以配置成 false，另外带参数的路由无需配置，直接就是不可见的。
     */
    private Boolean visible;
    /**
     *  菜单类名。
     */
    private String className;
}
