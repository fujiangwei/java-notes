package com.notes.spring.aop.annotation;

import java.lang.annotation.*;

/**
 * 文件描述
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnno {

}
