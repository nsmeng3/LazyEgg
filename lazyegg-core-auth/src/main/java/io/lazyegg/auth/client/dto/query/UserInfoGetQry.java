package io.lazyegg.auth.client.dto.query;

import com.alibaba.cola.dto.Query;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/10 9:53 下午
 */
@Getter
@Setter
public class UserInfoGetQry extends Query {

    private String authorization;

    public UserInfoGetQry(String authorization) {
        this.authorization = authorization;
    }
}
