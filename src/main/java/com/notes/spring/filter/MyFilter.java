package com.notes.spring.filter;

import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件描述 过滤器Filter
 **/
@WebFilter(filterName = "MyFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

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
        System.out.println("filter耗时:" + (stopWatch.getTotalTimeMillis()) + "ms---URL:" + url);
        System.out.println("filter耗时2:" + (System.currentTimeMillis() - start) + "ms---URL:" + url);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private boolean filterResUrl(String url) {
        String[] urls = {"/login", "/json", ".js", ".css", ".ico", ".jpg", ".png"};
        if (StringUtils.isEmpty(url)) {
            return Boolean.FALSE;
        }

        boolean result = Boolean.FALSE;
        for (int i = 0; i < urls.length; i++) {
            if (url.indexOf(urls[i]) != -1) {
                result = Boolean.TRUE;
                break;
            }
        }

        return result;
    }
}
