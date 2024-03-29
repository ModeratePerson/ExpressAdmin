package com.express.controller;

import com.express.bean.*;
import com.express.mvc.ResponseBody;
import com.express.mvc.ResponseView;
import com.express.service.CourierService;
import com.express.service.ExpressService;
import com.express.service.Imple.CourierServiceImple;
import com.express.service.Imple.ExpressServiceImple;
import com.express.service.Imple.UserServiceImple;
import com.express.service.UserService;
import com.express.utils.SMSSender;
import com.express.utils.ToJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class UserController {
    private UserService userService = new UserServiceImple();
    private CourierService courierService = new CourierServiceImple();
    private ExpressService expressService = new ExpressServiceImple();
    @ResponseBody("/admin/select_user.do")
    public String selectAll(HttpServletRequest request, HttpServletResponse response){
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pagenumber = Integer.parseInt(request.getParameter("pageNumber"));
        Integer count = userService.count();
        List<User> userList;
        userList = userService.selectPart(pagenumber, offset);
        ResultDataForUser resultData = new ResultDataForUser();
        resultData.setList(userList);
        resultData.setTotal(count);
        System.out.println(ToJsonUtil.toJson(resultData));
        return ToJsonUtil.toJson(resultData);
    }
    @ResponseBody("/admin/increase_user.do")
    public String increase(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        Boolean flag = userService.Increase(user);
        Message message = new Message();
        if (flag){
            message.setResult("添加成功");
            message.setStatus(1);
        }
        else {
            message.setStatus(0);
            message.setResult("添加失败");
        }
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/admin/select_by_phone.do")
    public String SelectByPhone(HttpServletRequest request, HttpServletResponse response){
        String phone = request.getParameter("phone");
        User user = userService.selectByPhone(phone);
        Message message = new Message();
        if (user!= null) {
            message.setData(user);
            message.setResult("查询成功");
            message.setStatus(1);
        }
        else {
            message.setStatus(0);
            message.setResult("查询失败");
        }
        return ToJsonUtil.toJson(message);

    }
    @ResponseBody("/admin/update_user.do")
    public String update(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        User user = new User();
        user.setPhone(phone);
        user.setName(name);
        Message message = new Message();
        Boolean flag = userService.Update(user,phone);
        if (flag){
            message.setStatus(1);
            message.setResult("修改成功");
        }
        else {
            message.setStatus(0);
            message.setResult("修改失败");

        }
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/admin/delete_user.do")
    public String delete(HttpServletRequest request, HttpServletResponse response){
        String phone = request.getParameter("phone");
        Boolean flag = userService.delete(phone);
        User user = userService.selectByPhone(phone);
        int id = user.getId();
        ExpressService expressService = new ExpressServiceImple();
        Boolean flag1 = expressService.DeleteBYUserId(id);
        Message message = new Message();
        if (flag&&flag1){
            message.setStatus(1);
            message.setResult("删除成功");
        }
        else {
            message.setStatus(0);
            message.setResult("删除失败");

        }
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/wx/getCode.do")
    public String getCode(HttpServletRequest request, HttpServletResponse response){
        String phone = request.getParameter("phone");
        int code = 100000 + new Random().nextInt(900000);
        String code1 = code + "";
        User user = userService.selectByPhone(phone);
        if (user==null){
            User user1 = new User();
            user1.setPhone(phone);
//            生成8位数字默认用户名
            int i = 10000000 + new Random().nextInt(90000000);
            user1.setName(i+"");
            userService.Increase(user1);
            userService.update_sign_time(phone);
        }
        userService.update_code(code1,phone);
        boolean send = SMSSender.send(phone, code1);
        userService.update_generate_time(phone);
        Message message = new Message();
        if (send){
            message.setResult("发送成功");
            message.setStatus(1);
        }
        else {
            message.setStatus(0);
            message.setResult("发送失败，请过1分钟后重试");
        }
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/wx/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
//        System.out.println(code);
        User user = userService.selectByPhone(phone);
//        判断登陆手机号是否是快递员
        Couriers couriers = courierService.selectByPhone(phone);
        if (couriers!=null){
//            根据status判断是否是快递员
            request.getSession().setAttribute("status",1+"");
        }
        String code1 = user.getCode();
        Date generateTime = user.getGenerate_time();
//        过期时间
        Date expirationTime =  new Date(generateTime.getTime() + 60000);
        Date now = new Date();
        Message message = null;
        if (now.after(expirationTime)||!(code1.equals(code))){
//            当前时间超过了过期时间
            message = new Message();
            message.setStatus(0);
            message.setResult("验证码错误或已失效");
            return ToJsonUtil.toJson(message);
        }
        userService.update_login_time(phone);
        request.getSession().setAttribute("phone",user);
        message = new Message();
        message.setResult("index.html");
        message.setStatus(1);
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/wx/exit.do")
    public String exit(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        Message message = new Message();
        message.setStatus(1);
        message.setResult("login.html");
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/wx/update_real_name.do")
    public String update_name(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
        User user = userService.selectByPhone(phone);
        user.setName(name);
        Message message = new Message();
        if (code.equals(user.getCode())){
            Boolean update = userService.Update(user, phone);
            if (update){
                message.setStatus(1);
                message.setResult("修改成功");
            }
            else {
                message.setStatus(0);
                message.setResult("修改失败");
            }
            return ToJsonUtil.toJson(message);
        }
        message.setStatus(0);
        message.setResult("修改失败");
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/wx/getQRCode.do")
    public String getQRCode(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("phone");
        String phone = user.getPhone();
//        查询未取件的express
        List<Express> expressList = expressService.SelectByPhone(phone,0);
        Message message = new Message();
        if (expressList!=null){
            message.setStatus(1);
            message.setData(expressList);
            return ToJsonUtil.toJson(message);
        }
        message.setStatus(0);
        message.setResult("加载失败，没有待取包裹");
        return ToJsonUtil.toJson(message);
    }
}
