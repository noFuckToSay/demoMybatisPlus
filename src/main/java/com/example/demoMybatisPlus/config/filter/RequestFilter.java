package com.example.demoMybatisPlus.config.filter;

import cn.hutool.core.util.StrUtil;
import com.example.demoMybatisPlus.config.mybatisPlus.OperatorContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Optional;

/**
 * @author:22024002
 * @date:2023/4/7 12:14
 * @description:
 */
@Slf4j
//@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        String operator= Optional.ofNullable(request.getHeader("operator")).orElse("张三");
        operator=URLDecoder.decode(operator,"UTF-8");
        if(StrUtil.isEmpty(operator)){
            //todo 未登录，重定向到登录
        }
        System.out.println("operator is "+operator);
        OperatorContextHolder.setOperator(operator);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("请求拦截器初始化...");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
