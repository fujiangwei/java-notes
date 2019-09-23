package com.notes.spring.aop.aspects.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 文件描述
 **/
@Slf4j(topic = "aop aspectj execution（切点） + interceptor（增强Advice）构成织入")
public class MyAopAdviceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("method {} is called in {} with arguments {} ", methodInvocation.getMethod(),
                methodInvocation.getThis(), methodInvocation.getArguments());
        Object proceed = methodInvocation.proceed();
        log.info("method {}, returns {}", methodInvocation.getMethod(), proceed);
        return proceed;
    }
}
