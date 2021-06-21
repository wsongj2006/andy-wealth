package com.andy.wealth.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.andy.wealth.beans.Person.get*())")
    public void personPointCut() {

    }

    @Before("personPointCut()")
    public void beforeAdvice() {
        System.out.println("前置通知。。。。。。");
    }
}
