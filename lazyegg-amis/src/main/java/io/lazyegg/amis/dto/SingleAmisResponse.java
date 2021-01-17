package io.lazyegg.amis.dto;

import io.lazyegg.amis.component.SchemaNode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Single
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 12:17 下午
 */

@Slf4j
@Getter
@Setter
public class SingleAmisResponse<T extends SchemaNode> extends AmisResponse{

    private T data;

    public static SingleAmisResponse buildSuccess(String msg) {
        SingleAmisResponse response = new SingleAmisResponse();
        response.setStatus(0);
        response.setMsg(msg);
        return response;
    }
    public static SingleAmisResponse buildSuccess() {
        SingleAmisResponse response = new SingleAmisResponse();
        response.setStatus(0);
        response.setMsg("");
        return response;
    }

    public static SingleAmisResponse buildFailure(int errCode, String msg) {
        SingleAmisResponse response = new SingleAmisResponse();
        if (errCode == 0) {
            errCode = 500;
        }
        response.setStatus(errCode);
        response.setMsg(msg);
        return response;
    }

    public static <T extends SchemaNode> SingleAmisResponse<T> of(T data) {
        SingleAmisResponse<T> response = new SingleAmisResponse<>();
        response.setStatus(0);
        response.setMsg("");
        response.setData(data);
        return response;
    }


}
