package com.user_messaging_system.core_library.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.user_messaging_system.core_library.common.annotation.LogExecution)")
    public void logExecutionPointcut() {}

    @Before("logExecutionPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("[START] Method {} called with parameters: {}", methodName, Arrays.toString(args));
    }

    @AfterReturning(value = "logExecutionPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("[SUCCESS] Method {} completed successfully. Response: {}", methodName, result);
    }

    @AfterThrowing(value = "logExecutionPointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("[ERROR] Method {} encountered an exception. Message: {}", methodName, exception.getMessage());
    }
}