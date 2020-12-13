package io.lazyegg.core;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/12 5:27 下午
 */
public class Test {

    public static void main(String[] args) {
        Response.buildSuccess();
        SingleResponse.of(null);
        MultiResponse.of(null, 0);
    }
}
