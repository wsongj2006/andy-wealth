package com.andy.wealth.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspectj {

    @Pointcut("execution(* com.andy.wealth.service.impl.UserServiceImpl.save(..))")
    public void myPointCut() {
    }

    @Pointcut("execution(* com.andy.wealth.service.impl.UserServiceImpl.getUserName(..))")
    public void myPointCutWithReturns() {
    }

    @Pointcut("execution(* com.andy.wealth.service.impl.UserServiceImpl.getAge(..))")
    public void myPointArount(){}

    @Before("myPointCut()")
    public void beforeAdvice() {
        System.out.println("前置通知。。。。。。");
    }

    @AfterReturning(value = "myPointCutWithReturns()", returning = "returnValue")
    public void afterReturning(Object returnValue) {
        System.out.println("后置增强。。。" + returnValue);
    }


    @Around("myPointArount()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("开始获取age...");
        int age = Integer.parseInt(proceedingJoinPoint.proceed().toString());
        System.out.println("结束获取age..." + age);
    }
}
