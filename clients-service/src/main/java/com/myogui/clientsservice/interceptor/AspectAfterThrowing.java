package com.myogui.clientsservice.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAfterThrowing {

    Logger logger = LogManager.getLogger(AspectAfterThrowing.class);

    @Pointcut("execution (* com.myogui.clientsservice.service.ClientService.update(..))")
    void afterThrowingUpdate() {}

    @AfterThrowing("afterThrowingUpdate()")
    void afterExceptionUpdate(JoinPoint jp) {
        logger.info("Se detuvo la ejecucion por una exception.");
    }
}
