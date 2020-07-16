/*
package com.zhanghui.yx.crossDomainUtil;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

*/
/**
 * 解决Ajax的跨域问题
 * 为响应设置响应头
 * Access-Control-Allow-Origin ---> 设置访问白名单
 *
 * @author 张辉
 *//*

@Component
public class CorsFilter implements Filter {

    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        //获取前台传过来的的域名,   参数：Origin：是前台传递域名的key
        String originHeader = ((HttpServletRequest) req).getHeader("Origin");
        //添加允许访问的域名
        ArrayList<String> domainList = new ArrayList<>();
        domainList.add("http://192.168.32.1:8989");
        domainList.add("http://192.168.32.1:8888");
        //判断该域名是否在白名单中
        if (domainList.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}*/
