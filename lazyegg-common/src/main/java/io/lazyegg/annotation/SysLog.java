package io.lazyegg.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/16 11:07 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
