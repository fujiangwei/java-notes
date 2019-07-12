# Filter

> 介绍
```$xslt
Filter是依赖于Servlet容器，属于Servlet规范的一部分，所以它的执行由Servlet容器回调完成，生命周期也由Servlet管理
```
> 使用
* 实现Filter接口


    public class MyFilter implements Filter {
    
        private static final ThreadLocal<Long> tl = new ThreadLocal<Long>();
    
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            System.out.println("coming filter...");
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String url = request.getRequestURI() + "?" + request.getQueryString();
            boolean isOk = filterResUrl(url);
            if (isOk) {
                return;
            }
            long start = System.currentTimeMillis();
            StopWatch stopWatch = new StopWatch(url + start);
            stopWatch.start();
            filterChain.doFilter(servletRequest, servletResponse);
            stopWatch.stop();
            System.out.println("耗时:" + (stopWatch.getTotalTimeMillis()) + "ms---URL:" + url);
            System.out.println("耗时2:" + (System.currentTimeMillis() - start) + "ms---URL:" + url);
        }
    
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
    
        }
    
        @Override
        public void destroy() {
    
        }
    
        private boolean filterResUrl(String url) {
            String[] urls = {"/login","/json",".js",".css",".ico",".jpg",".png"};
            if (StringUtils.isEmpty(url)) {
                 return Boolean.FALSE;
            }
    
            boolean result = Boolean.FALSE;
            for (int i = 0; i < urls.length; i ++) {
                 if (url.indexOf(urls[i]) != -1) {
                     result= Boolean.TRUE;
                     break;
                 }
            }
    
            return result;
        }
    }
在difilter中做一些业务逻辑处理
* 配置Filter  
 1、以前在web项目中通过web.xml进行配置，而在springboot项目中使用FilterRegistrationBean来完成
    
    
    @Configuration
    public class MyFilterConfig {
    
        @Bean
        public FilterRegistrationBean registrationBean() {
            FilterRegistrationBean registrationBean = new FilterRegistrationBean();
            registrationBean.setFilter(new MyFilter());
            registrationBean.addUrlPatterns("/*");
            registrationBean.setName("MyFilter");
            registrationBean.setOrder(1);
            return registrationBean;
        }
    }
    
2、或者通过WebFilter注解来完成,添加过滤url之类的，然后使用@ServletComponentScan注解来生效
    
    @WebFilter(filterName = "MyFilter", urlPatterns = "/*")
    public class MyFilter implements Filter {
    
        private static final ThreadLocal<Long> tl = new ThreadLocal<Long>();
    
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            System.out.println("coming filter...");
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String url = request.getRequestURI() + "?" + request.getQueryString();
            boolean isOk = filterResUrl(url);
            if (isOk) {
                return;
            }
            long start = System.currentTimeMillis();
            StopWatch stopWatch = new StopWatch(url + start);
            stopWatch.start();
            filterChain.doFilter(servletRequest, servletResponse);
            stopWatch.stop();
            System.out.println("耗时:" + (stopWatch.getTotalTimeMillis()) + "ms---URL:" + url);
            System.out.println("耗时2:" + (System.currentTimeMillis() - start) + "ms---URL:" + url);
        }
    
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
    
        }
    
        @Override
        public void destroy() {
    
        }
    
        private boolean filterResUrl(String url) {
            String[] urls = {"/login","/json",".js",".css",".ico",".jpg",".png"};
            if (StringUtils.isEmpty(url)) {
                 return Boolean.FALSE;
            }
    
            boolean result = Boolean.FALSE;
            for (int i = 0; i < urls.length; i ++) {
                 if (url.indexOf(urls[i]) != -1) {
                     result= Boolean.TRUE;
                     break;
                 }
            }
    
            return result;
        }
    }
    
    @SpringBootApplication
    @ServletComponentScan(basePackages = {"com.notes.spring.filter"})
    public class JavaNotesApplication {
    
    	public static void main(String[] args) {
    		SpringApplication.run(JavaNotesApplication.class, args);
    	}
    
    }
  
3、请求测试看看是否生效  