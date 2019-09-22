package com.java.redis.component;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("========================== MyFilter init...");
        log.info("========================== MyFilter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String operateId = UUID.randomUUID().toString();
        MDC.put("operateId", operateId);
//        System.out.println("========================== MyFilter doFilter...");
        log.info("========================== MyFilter doFilter...");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
//        System.out.println("========================== MyFilter destroy...");
        log.info("========================== MyFilter destroy...");
    }
}
