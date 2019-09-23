package com.notes.spring.aop.config;

import com.notes.spring.aop.annotation.MyAnno;
import com.notes.spring.aop.aspects.intercepter.MyAopAdviceInterceptor;
import com.notes.spring.aop.aspects.intercepter.MyAopAnnoInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件描述
 **/
@Configuration
@Slf4j(topic = "MyAnnoInterceptorConfig 配置")
public class MyAnnoInterceptorConfig {

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor2() {
        MyAopAnnoInterceptor interceptor = new MyAopAnnoInterceptor();
//        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(MyAnno.class);
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns("com.notes.spring.aop.service.impl.UserServiceImp.*");

        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
