package com.express.controller;

import com.express.bean.AdminUser;
import com.express.mvc.ResponseBody;
import com.express.mvc.ResponseView;
import com.express.service.AdminService;
import com.express.service.Imple.AdminServiceImple;
import com.express.bean.Message;
import com.express.utils.ToJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AdminLogin {
    @ResponseBody("/admin/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
//        System.out.println(username);
        String password = request.getParameter("password");
        AdminService adminService = new AdminServiceImple();
        AdminUser user = adminService.login(username, password);
        Message message = null;
        Date date = new Date();
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        System.out.println(date);
        if (user!=null){
            message = new Message("登录成功",1,user);
            request.getSession().setAttribute("username",username);
            adminService.UpdateIpAndTime(ip,date,username);
        }
        else {
            message = new Message("登录失败",0);
        }
//        System.out.println(ToJsonUtil.toJson(message));
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/admin/exit.do")
    public String exit(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        Message message = new Message();
        message.setStatus(1);
        message.setResult("login.html");
        return ToJsonUtil.toJson(message);
    }
}
