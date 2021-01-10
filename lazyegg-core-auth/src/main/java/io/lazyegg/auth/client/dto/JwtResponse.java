package io.lazyegg.auth.client.dto;

import com.alibaba.cola.dto.DTO;
import com.alibaba.cola.dto.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * JwtResponse
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/10 6:43 下午
 */
@Getter
@Setter
public class JwtResponse extends Response {

    private String token;



    public static JwtResponse buildSuccess(String token) {
        JwtResponse response = new JwtResponse();
        response.setSuccess(true);
        response.setToken(token);
        return response;
    }

    public static JwtResponse buildFailure(String msg) {
        JwtResponse response = new JwtResponse();
        response.setSuccess(false);
        response.setErrMessage(msg);
        return response;
    }
}
