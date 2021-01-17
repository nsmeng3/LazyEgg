package io.lazyegg.amis.dto;

import io.lazyegg.amis.component.SchemaNode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * MulitAmisResponse
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:15 下午
 */

@Slf4j
@Getter
@Setter
public class MultiAmisResponse<T extends SchemaNode> extends AmisResponse{

    private Collection<T> data;

    public static MultiAmisResponse buildSuccess(String msg) {
        MultiAmisResponse response = new MultiAmisResponse();
        response.setStatus(0);
        response.setMsg(msg);
        return response;
    }
    public static MultiAmisResponse buildSuccess() {
        MultiAmisResponse response = new MultiAmisResponse();
        response.setStatus(0);
        response.setMsg("");
        return response;
    }

    public static MultiAmisResponse buildFailure(int errCode, String msg) {
        MultiAmisResponse response = new MultiAmisResponse();
        if (errCode == 0) {
            errCode = 500;
        }
        response.setStatus(errCode);
        response.setMsg(msg);
        return response;
    }

    public static <T extends SchemaNode> MultiAmisResponse<T> of(Collection<T> data) {
        MultiAmisResponse<T> response = new MultiAmisResponse<T>();
        response.setStatus(0);
        response.setData(data);
        return response;
    }


}
