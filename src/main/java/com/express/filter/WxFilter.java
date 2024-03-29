package com.express.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WxFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter();");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object user = request.getSession().getAttribute("phone");
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(path);
        if (path.endsWith("/login.html") || path.endsWith("/login.do") || path.endsWith("/getCode.do") || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith("jpg")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (user != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 否则，重定向到登录页面
        response.sendRedirect("/wx/login.html");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
