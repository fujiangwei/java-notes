package com.notes.spring.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件描述 Interceptor拦截器
 **/
public class MyInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> tl = new ThreadLocal<Long>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("coming interceptor...");
        tl.set(System.currentTimeMillis());
        //返回为false将不会执行postHandle方法
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = request.getRequestURI() + "?" + request.getQueryString();
        System.out.println("interceptor耗时:" + (System.currentTimeMillis() - tl.get()) + "ms---URL:" + url);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
