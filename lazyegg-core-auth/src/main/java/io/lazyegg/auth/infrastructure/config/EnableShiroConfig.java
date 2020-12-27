package io.lazyegg.auth.infrastructure.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动Shiro注解
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/16 11:37 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ShiroConfiguration.class)
public @interface EnableShiroConfig {
}
