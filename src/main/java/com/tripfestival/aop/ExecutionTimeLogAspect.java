package com.tripfestival.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionTimeLogAspect {

    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        return result;
    }

    @Around("@annotation(ExecutionTimeLog)")
    public Object excutionTimeLog(ProceedingJoinPoint pjp) throws Throwable {

        long beforeTime = System.currentTimeMillis();

        Object result = pjp.proceed();

        long afterTime = System.currentTimeMillis();

        log.info("Execution Time : " + (afterTime - beforeTime) + "ms");

        return result;

    }
}
