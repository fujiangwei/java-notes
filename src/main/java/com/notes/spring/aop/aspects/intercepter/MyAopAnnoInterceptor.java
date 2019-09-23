package com.notes.spring.aop.aspects.intercepter;

import com.notes.spring.aop.annotation.MyAnno;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * 文件描述
 **/
@Slf4j(topic = "MyAopAnnoInterceptor 构成织入")
public class MyAopAnnoInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("method {} is called in {} with arguments {} ", methodInvocation.getMethod(),
                methodInvocation.getThis(), methodInvocation.getArguments());
        MyAnno myAnno = getMyAnno(methodInvocation.getMethod());
        if(null != myAnno) {
            Object proceed = methodInvocation.proceed();
            log.info("yes method {}, returns {}", methodInvocation.getMethod(), proceed);
            return proceed;
        } else {
            log.info("method {} 上没有MyAnno注解", methodInvocation.getMethod());
            return "无MyAnno注解";
        }
    }

    private MyAnno getMyAnno(Method method) {
        if (method.isAnnotationPresent(MyAnno.class)) {
            return method.getAnnotation(MyAnno.class);
        }

        return null;
    }

}
