package io.lazyegg.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 错误页
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/9 7:21 下午
 */
@Controller
public class ErrorPageController extends BaseController {

    @GetMapping("/invalid-api")
    @ResponseBody
    public Response invalidApi() {
        return SingleResponse.buildFailure("invalid-api", "invalid-api");
    }

    @GetMapping("/sys-exception")
    @ResponseBody
    public Response sysException() {
        return SingleResponse.buildFailure("sys-exception", "sys-exception");
    }
}
