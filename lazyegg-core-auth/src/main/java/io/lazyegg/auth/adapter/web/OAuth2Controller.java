package io.lazyegg.auth.adapter.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.SysException;
import io.lazyegg.auth.app.executor.AuthorizationCodeGetCmdExe;
import io.lazyegg.auth.app.executor.LeggOAuthTokenGetCmdExe;
import io.lazyegg.auth.client.OAuthTokenServiceI;
import io.lazyegg.auth.client.dto.AuthorizationCodeGetCmd;
import io.lazyegg.auth.client.dto.OAuthTokenGetCmd;
import io.lazyegg.constants.ErrCode;
import io.lazyegg.constants.RequestParamType;
import io.lazyegg.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * OAuth2 Controller
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/28 11:16 下午
 */
@RequestMapping("/oauth")
@RestController
public class OAuth2Controller extends BaseController {


    @Resource
    private OAuthTokenServiceI oAuthTokenService;

    /**
     * 授权
     *
     * @return
     */
    @GetMapping("/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response) {
        AuthorizationCodeGetCmd authorizationCodeGetCmd = requestParams(request, AuthorizationCodeGetCmd.class, RequestParamType.Query);
        String callbackUrl = oAuthTokenService.authorizeRedirect(authorizationCodeGetCmd);
        try {
            response.sendRedirect(callbackUrl);
        } catch (IOException e) {
            String errCode = ErrCode.UserErr.UserReqServiceException.A0500.name();
            String errMessage = ErrCode.UserErr.UserReqServiceException.A0500.getErrMessage();
            throw new SysException(errCode, errMessage);
        }
    }

    /**
     * 获取令牌
     */
    @PostMapping("/access_token")
    public Response accessToken() {
        OAuthTokenGetCmd OAuthTokenGetCmd = requestParams(OAuthTokenGetCmd.class);
        Response execute = oAuthTokenService.accessToken(OAuthTokenGetCmd);
        return execute;

    }
}
