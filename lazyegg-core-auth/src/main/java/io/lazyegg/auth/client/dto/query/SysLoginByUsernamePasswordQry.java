package io.lazyegg.auth.client.dto.query;

import com.alibaba.cola.dto.Query;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:05 下午
 */
@Getter
@Setter
public class SysLoginByUsernamePasswordQry extends Query {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;
    /**
     * 验证码uuid
     */
    private String uuid;

    public SysLoginByUsernamePasswordQry(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
