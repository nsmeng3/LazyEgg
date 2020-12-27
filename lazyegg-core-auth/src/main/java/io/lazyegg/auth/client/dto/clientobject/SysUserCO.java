package io.lazyegg.auth.client.dto.clientobject;

import lombok.Data;

/**
 * 用户CO
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/20 3:23 下午
 */
@Data
public class SysUserCO {

    private Long userId;
    private String username;
    private String password;
    private String token;

}
