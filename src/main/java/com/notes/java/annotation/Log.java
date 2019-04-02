package com.notes.java.annotation;

import java.lang.annotation.*;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/31
 * @time: 23:48
 * @modifier:
 * @since:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

}
