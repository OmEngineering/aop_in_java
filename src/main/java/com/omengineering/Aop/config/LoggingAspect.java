package com.omengineering.Aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.*;
import org.springframework.stereotype.Component;


/**
 * LoggingAspect
 */

@Aspect 
@Component
public class LoggingAspect {

     private final Logger logger = LoggerFactory.getLogger(this.getClass());

     @Before("execution(* com.omengineering.Aop.service.EmployeeService.*(..))")
     public void logBeforMethod(JoinPoint jointPoint){
        logger.info("Method called: "+ jointPoint.getSignature().getName());
     }

     @AfterReturning (value ="execution(* com.omengineering.Aop.service.EmployeeService.*(..))", returning = "result")
     public void logAfterMethod(JoinPoint joinPoint,Object result){
        logger.info("Method Executed: "+joinPoint.getSignature().getName());
        logger.info("Result: "+result);
     }
}