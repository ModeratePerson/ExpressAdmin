package com.express.mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatchServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        String path = config.getInitParameter("contentConfigLocation");
        InputStream resource = DispatchServlet.class.getClassLoader().getResourceAsStream(path);
        HandlerMapping.load(resource);

    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        HandlerMapping.MVCMapping mvcMapping = HandlerMapping.get_uri(uri);
        if (mvcMapping!=null){
            Method method = mvcMapping.getMethod();
            Object object = mvcMapping.getObject();
            Object result = null;
            try {
                result = method.invoke(object,req,resp);
                switch (mvcMapping.getType()){
                    case Text:
                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write((String) result);
                        resp.getWriter().flush();
                        break;
                    case View:
                        resp.sendRedirect((String) result);
                        break;
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            resp.sendError(404,"找不到请求的地址:"+uri);
        }
    }

}
