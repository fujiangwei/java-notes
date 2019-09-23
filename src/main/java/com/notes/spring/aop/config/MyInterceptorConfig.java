package com.notes.spring.aop.config;

import com.notes.spring.aop.aspects.intercepter.MyAopAdviceInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件描述
 **/
@Configuration
@Slf4j(topic = "MyInterceptorConfig 配置")
public class MyInterceptorConfig {

    public static final String traceExecution = "execution(* com.notes.spring.aop.service.impl.*.*(..))";

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        MyAopAdviceInterceptor interceptor = new MyAopAdviceInterceptor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(traceExecution);

        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
