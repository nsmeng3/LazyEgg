package io.lazyegg.aspect;

import io.lazyegg.annotation.SysLog;
import io.lazyegg.log.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.Future;

/**
 * 系统日志切面
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/16 11:10 下午
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Resource
    private LogService logService;

    /**
     * 切点
     */
    @Pointcut("@annotation(io.lazyegg.annotation.SysLog))")
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
        logService.save(point, time);
        return result;
    }
}
