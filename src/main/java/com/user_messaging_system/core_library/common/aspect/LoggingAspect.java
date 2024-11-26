package com.user_messaging_system.core_library.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.user_messaging_system.core_library.common.annotation.LogExecution)")
    public void logExecutionPointcut() {}

    @Before("logExecutionPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = getCurrentMethod(joinPoint).getName();
        Object[] args = joinPoint.getArgs();

        String params = Arrays.stream(args)
                          .map(arg -> arg != null ? arg.toString() : "null")
                          .collect(Collectors.joining(", "));

       logger.info(
               "[START] [{}] [{}] - Method called with parameters: [{}]",
               className,
               methodName,
               params
       );
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

    private Method getCurrentMethod(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}