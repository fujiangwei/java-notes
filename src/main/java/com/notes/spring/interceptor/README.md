# Interceptor
> 介绍
```$xslt
spring 拦截器一般是通过动态代理来实现的，通过Ioc容器进行管理
```
> 使用

1、实现HandlerInterceptor接口

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
2、配置实现WebMvcConfigurer

    @Configuration
    public class InterceptorConfig implements WebMvcConfigurer {
    
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
        }
    }
    
3、请求测试看看是否生效

> 应用场景
* 登录验证，判断用户是否登录。
* 权限验证，判断用户是否有权限访问资源，如校验token
* 日志记录，记录请求操作日志（用户ip，访问时间等），以便统计请求访问量。
* 处理cookie、本地化、国际化、主题等。
* 性能监控，监控请求处理时长等。