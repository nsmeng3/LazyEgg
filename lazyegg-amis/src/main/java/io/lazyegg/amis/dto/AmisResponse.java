package io.lazyegg.amis.dto;

import com.alibaba.cola.dto.DTO;
import com.alibaba.cola.dto.Response;
import io.lazyegg.amis.component.SchemaNode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * amis响应
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/17 11:58 上午
 */

@Slf4j
@Getter
@Setter
public class AmisResponse extends DTO {

    private Integer status;
    private String msg;


    public static AmisResponse buildSuccess(String msg) {
        AmisResponse response = new AmisResponse();
        response.setStatus(0);
        response.setMsg(msg);
        return response;
    }
    public static AmisResponse buildSuccess() {
        AmisResponse response = new AmisResponse();
        response.setStatus(0);
        response.setMsg("");
        return response;
    }

    public static AmisResponse buildFailure(int errCode, String msg) {
        AmisResponse response = new AmisResponse();
        if (errCode == 0) {
            errCode = 500;
        }
        response.setStatus(errCode);
        response.setMsg(msg);
        return response;
    }

}
