package io.lazyegg.auth.app.executor.query;

import io.lazyegg.auth.client.dto.JwtResponse;
import io.lazyegg.auth.client.dto.query.SysLoginByUsernamePasswordQry;
import io.lazyegg.auth.domain.gateway.SysUserGateway;
import io.lazyegg.auth.domain.model.SysUser;
import io.lazyegg.constants.Constants;
import io.lazyegg.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

/**
 * 用户名密码登录
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 10:16 下午
 */
@Slf4j
@Component
public class SysLoginByUsernamePasswordQryExe {

    public static final HashMap<String, SysUser> cacheToken = new HashMap<>();
    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;
    @Autowired
    private SysUserGateway sysUserGateway;

    public JwtResponse execute(SysLoginByUsernamePasswordQry query) {

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(query.getUsername(), query.getPassword());
        try {
            // 登录成功
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);
            // 生成uuid
            String token = UUID.randomUUID().toString();
            // 包装loginUser
            SysUser sysUser = sysUserGateway.getByUsername(usernamePasswordToken.getUsername());
            sysUser.setAccessToken(token);
            // 刷新token
            refreshToken(sysUser);

            HashMap<String, Object> claims = new HashMap<>();
            claims.put(Constants.LOGIN_USER_KEY, token);
            // 将loginUser以uuid为key并设置有效期缓存起来

            // 将uuid构造jwt 传给用户端，有效期30秒
            // 客户端拿到jwt 携带jwt访问 getUserInfo接口获取loginUser对象
            // 当调用其他接口时，使用loginUser提供的access token作为令牌携带在header中


            return JwtResponse.buildSuccess(createToken(claims));
        } catch (AuthenticationException e) {
            log.error(e.getMessage(), e);
            return JwtResponse.buildFailure(e.getMessage());
        }


//        Subject subject = SecurityUtils.getSubject();
//        Collection collection = subject.getPrincipals().fromRealm(JDBCRealm.REALM_NAME);
//        subject.login(token);
    }

    private String createToken(HashMap<String, Object> claims) {
        return JwtUtils.createJWT(secret, claims);
    }

    /**
     * 刷新令牌有效期
     *
     * @param sysUser 登录信息
     */
    public void refreshToken(SysUser sysUser) {
        sysUser.setLoginTime(System.currentTimeMillis());
        sysUser.setExpireTime(sysUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(sysUser.getAccessToken());
        cacheToken.put(userKey, sysUser);
//        redisCache.setCacheObject(userKey, sysUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String accessToken) {
        return Constants.LOGIN_TOKEN_KEY + accessToken;
    }
}
