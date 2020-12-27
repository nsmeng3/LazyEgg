package io.lazyegg.aspect;

import io.lazyegg.log.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 访问日志
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 5:30 下午
 */

@Slf4j
@Aspect
@Component
public class AssessLogAspect {

    @Resource
    private LogService logService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patchMapping() {
    }

    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object proceed = null;
        try {
            //执行方法
            proceed = point.proceed();
        } catch (Throwable throwable) {
            long time = System.currentTimeMillis() - beginTime;
            logService.save(point, time,throwable);
            throw throwable;
        }
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        logService.save(point, time);
        return proceed;
    }

    @Around("requestMapping()")
    public Object requestMappingAround(ProceedingJoinPoint point) throws Throwable {
        return around(point);
    }

    @Around("getMapping()")
    public Object getMappingAround(ProceedingJoinPoint point) throws Throwable {
        return around(point);
    }

    @Around("postMapping()")
    public Object postMappingAround(ProceedingJoinPoint point) throws Throwable {
        return around(point);
    }

    @Around("putMapping()")
    public Object putMappingAround(ProceedingJoinPoint point) throws Throwable {
        return around(point);
    }

    @Around("deleteMapping()")
    public Object deleteMappingAround(ProceedingJoinPoint point) throws Throwable {
        return around(point);
    }

    @Around("patchMapping()")
    public Object patchMappingAround(ProceedingJoinPoint point) throws Throwable {
        return around(point);
    }


}
