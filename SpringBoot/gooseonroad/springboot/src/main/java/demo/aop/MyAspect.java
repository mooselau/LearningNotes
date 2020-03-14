package demo.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* UserServiceImpl.printUser(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        p("===> MyAspect::before()..");
    }

    @After("pointCut()")
    public void after() {
        p("===> MyAspect::after()..");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        p("===> MyAspect::afterReturning()..");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        p("===> MyAspect::afterThrowing()..");
    }

    private void p(String msg) {
        System.out.println(msg);
    }
}