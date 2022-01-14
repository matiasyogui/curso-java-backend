package com.myogui.clientsservice.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAfter {
    Logger logger = LogManager.getLogger(AspectAfter.class);

    @Pointcut("execution(* com.myogui.clientsservice.service.ClientService.update(..))")
    void alTerminarUpdate(){}

    @After("alTerminarUpdate()")
    void afterUpdate(JoinPoint jp) {
        String method = jp.getSignature().getName();
        logger.info(method);
    }

    @Pointcut("execution(* com.myogui.clientsservice.service.ClientService.delete(..))")
    void alTerminarDelete() {}

    @After("alTerminarDelete()")
    void afterDelete(JoinPoint jp) {
        String method = jp.getSignature().getName();
        logger.info(method);
    }
}
