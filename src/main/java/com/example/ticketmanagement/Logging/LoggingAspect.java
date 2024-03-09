package com.example.ticketmanagement.Logging;

import com.example.ticketmanagement.Controller.TicketController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Aspect
@Component
@Document
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

    @Before("execution(* com.example.ticketmanagement.Repository.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Before executing MongoDB query at " +methodName+ formatLocalDateTime(LocalDateTime.now()));
    }

    @AfterReturning(pointcut = "execution(* com.example.ticketmanagement.Repository.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("After executing MongoDB query at " +result + methodName + formatLocalDateTime(LocalDateTime.now()));
    }

    @Before("execution(* com.example.ticketmanagement.Controller.*.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Before executing API method at "+methodName + formatLocalDateTime(LocalDateTime.now()));
    }

    @AfterReturning(pointcut = "execution(* com.example.ticketmanagement.Controller.*.*(..))", returning = "result")
    public void logAfterReturningController(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("After executing API method at "+result + methodName +formatLocalDateTime(LocalDateTime.now()));
    }
}

