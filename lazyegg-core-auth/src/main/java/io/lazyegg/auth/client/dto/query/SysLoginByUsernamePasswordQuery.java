package io.lazyegg.auth.client.dto.query;

import com.alibaba.cola.dto.Query;

/**
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:05 下午
 */
public class SysLoginByUsernamePasswordQuery extends Query {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public SysLoginByUsernamePasswordQuery(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
