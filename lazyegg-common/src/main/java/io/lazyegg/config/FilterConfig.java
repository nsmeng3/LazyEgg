package io.lazyegg.config;

import io.lazyegg.exception.GlobalFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/2 11:35 下午
 */
@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<GlobalFilter> filterRegistrationBean() {
        log.debug("注册全局过滤器...");
        FilterRegistrationBean<GlobalFilter> filterRegistrationBean = new FilterRegistrationBean<GlobalFilter>();
        filterRegistrationBean.setFilter(new GlobalFilter());
        filterRegistrationBean.setName("global");
        filterRegistrationBean.setOrder(-10000);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
