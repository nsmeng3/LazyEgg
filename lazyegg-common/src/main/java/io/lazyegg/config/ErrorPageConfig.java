package io.lazyegg.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

/**
 * 错误页配置
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/9 7:17 下午
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage invalidApi = new ErrorPage(HttpStatus.NOT_FOUND, "/invalid-api");
        ErrorPage sysException = new ErrorPage(ServletException.class, "/sys-exception");
        registry.addErrorPages(sysException, invalidApi);
    }
}
