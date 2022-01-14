package com.myogui.clientsservice.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class AspectAround {
    Logger logger = LogManager.getLogger(AspectAround.class);

    @Pointcut("within(@org.springframework.stereotype.Service *) && " + "!(execution(* com.myogui.clientsservice.service.ClientService.delete(..)))")
    void aroundServiceMethods() {}

    @Around("aroundServiceMethods()")
    Object aroundServiceMethodsAdvice(ProceedingJoinPoint jp) throws Throwable {
        long start = System.nanoTime();
        Object ret = jp.proceed();
        long end = System.nanoTime();

        String method = jp.getSignature().getName();

        logger.info("Se ejecuto " + method + "  con un tiempo de " + (TimeUnit.NANOSECONDS.toMillis(end - start)));
        return ret;
    }
}
