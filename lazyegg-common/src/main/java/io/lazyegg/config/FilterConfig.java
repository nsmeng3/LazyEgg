package io.lazyegg.config;

import io.lazyegg.exception.GlobalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/2 11:35 下午
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<GlobalFilter> filterRegistrationBean() {
        FilterRegistrationBean<GlobalFilter> filterRegistrationBean = new FilterRegistrationBean<GlobalFilter>();
        filterRegistrationBean.setFilter(new GlobalFilter());
        filterRegistrationBean.setName("global");
        filterRegistrationBean.setOrder(-10000);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
