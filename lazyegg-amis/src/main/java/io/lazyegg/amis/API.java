package io.lazyegg.amis;

import io.lazyegg.amis.component.Expression;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * API 类型用于配置请求接口的格式，涉及请求方式、请求地址、请求数据体等等相关配置
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/16 9:09 下午
 */

@Slf4j
@Getter
@Setter
public class API {

    /**
     * 请求方式		支持：get、post、put、delete
     */
    private String method;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求数据 支持数据映射
     */
    private Data data;
    /**
     * 数据体格式
     * 默认为 json 可以配置成 form 或者 formdata。
     * 当 data 中包含文件时，自动会采用 formdata（multipart/formdata） 格式。
     * 当配置为 form 时为 application/xwwwformurlencoded 格式。
     */
    private DataType dataType;
    /**
     * 当 dataType 为 form 或者 formdata 的时候有用。
     * 具体参数请参考这里，默认设置为: { arrayFormat: 'indices', encodeValuesOnly: true }
     */
    private ArrayList<QsOption> qsOptions;
    /**
     * 请求头
     */
    private ArrayList<Header> headers;
    /**
     * 请求条件
     */
    private Expression sendOn;
    /**
     * 接口缓存时间
     */
    private Integer cache;
    /**
     * 发送适配器		，支持String串格式，或者直接就是函数如：
     */
    private String requestAdaptor;
    /**
     * 接收适配器		如果接口返回不符合要求，可以通过配置一个适配器来处理成 amis 需要的。同样支持 Function 或者 String函数体格式
     */
    private String adaptor;
    /**
     * 替换当前数据		返回的数据是否替换掉当前的数据，默认为 false，即：追加，设置成 true 就是完全替换。
     */
    private Boolean replaceData;
    /**
     * 返回类型		如果是下载需要设置为 'blob'
     */
    private String responseType;
    /**
     * 是否自动刷新		配置是否需要自动刷新接口。
     */
    private Boolean autoRefresh;
}
