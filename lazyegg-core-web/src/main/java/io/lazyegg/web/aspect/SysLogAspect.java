package io.lazyegg.web.aspect;

import io.lazyegg.web.annotation.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 系统日志切面
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/16 11:10 下午
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(io.lazyegg.web.annotation.SysLog))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        logSave(point, time);

        return result;
    }

    private void logSave(ProceedingJoinPoint joinPoint, long time) {
        log.info("执行时间{}ms", time);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog annotation = method.getAnnotation(SysLog.class);
        String value = annotation.value();
        log.info("执行了[{}]", value);
        // TODO 存储系统日志，及更多日志信息收集
    }
}
