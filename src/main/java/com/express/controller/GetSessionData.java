package com.express.controller;

import com.express.bean.Message;
import com.express.mvc.ResponseBody;
import com.express.utils.ToJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSessionData {
    @ResponseBody("/admin/get_session.do")
    public String GetData(HttpServletRequest request, HttpServletResponse response){
        String argument = request.getParameter("argument");
//        System.out.println(argument);
        String value =  (String) request.getSession().getAttribute(argument);
//        System.out.println(value);
        Message message = new Message();
        message.setResult(value);
        System.out.println(ToJsonUtil.toJson(message));
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/wx/get_session.do")
    public String GetWXData(HttpServletRequest request, HttpServletResponse response){
        String argument = request.getParameter("argument");
//        System.out.println(argument);
        Object o = request.getSession().getAttribute(argument);
//        System.out.println(value);
        Message message = new Message();
        if (o!=null){
            message.setData(o);
            message.setStatus(1);
            return ToJsonUtil.toJson(message);
        }
        message.setStatus(0);
//        System.out.println(ToJsonUtil.toJson(message));
        return ToJsonUtil.toJson(message);
    }
}
