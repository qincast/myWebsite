package com.example.logindemo.aop;

import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAop {
    @Before("execution(* com.sharpcj.aopdemo.test1.IBuy.buy(..))")
    public void test(){

    }
}
