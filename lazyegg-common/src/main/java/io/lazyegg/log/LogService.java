package io.lazyegg.log;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 日志service
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/19 7:26 下午
 */
@Slf4j
@Component
public class LogService {

    @Async("logExecutor")
    @SneakyThrows
    public void save(ProceedingJoinPoint point, long endTime, Throwable throwable) {
        String className = point.getSourceLocation().getWithinType().getName();
        String methodName = point.getSignature().getName();
        if (throwable == null) {
            log.debug("执行--->>{}#{}()-消耗时间->{}ms", className, methodName, endTime);
        } else {
            log.error("执行异常--->>{}#{}()-[{}]消耗时间->{}ms",className, methodName, throwable.getMessage(), endTime, throwable);
        }
    }

    @Async("logExecutor")
    @SneakyThrows
    public void save(ProceedingJoinPoint point, long endTime) {
        save(point, endTime, null);
    }
}
