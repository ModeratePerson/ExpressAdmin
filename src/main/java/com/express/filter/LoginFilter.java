package com.express.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LoginFilter implements Filter {
//private Set<String> urlPatterns;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      System.out.println("filter();");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object user = request.getSession().getAttribute("username");
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(path);
        if (path.endsWith("/login.html")||path.endsWith("/login.do") || path.endsWith(".css") || path.endsWith(".js")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (user != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 否则，重定向到登录页面
        response.sendRedirect("/admin/login.html");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
//    private boolean isAllowed(String url) {
//        // 检查请求的URL路径是否在白名单中
//        for (String pattern : urlPatterns) {
//            if (url.matches(pattern)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
