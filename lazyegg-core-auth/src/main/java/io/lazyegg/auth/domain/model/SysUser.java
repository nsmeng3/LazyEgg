package io.lazyegg.auth.domain.model;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

/**
 * 用户
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 9:48 下午
 */
@Data
@Entity
public class SysUser {

    private Long userId;

    private String username;

    private String password;

    private String accessToken;

    private String refreshToken;

    private Long loginTime;

    private Long expireTime;

    /**
     * 盐
     */
    private String salt;

    /**
     * 加盐凭证
     */
    private String credentialsSalt;


    /**
     * 清除密码
     *
     * @return
     */
    public SysUser clearPassword() {
        this.password = null;
        return this;
    }

}
