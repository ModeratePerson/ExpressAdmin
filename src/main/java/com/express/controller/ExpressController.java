package com.express.controller;

import com.express.bean.*;
import com.express.mvc.ResponseBody;
import com.express.service.ExpressService;
import com.express.service.Imple.ExpressServiceImple;
import com.express.utils.ToJsonUtil;
import com.express.utils.ToNewExpress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpressController {
    private ExpressService expressService = new ExpressServiceImple();
    @ResponseBody("/admin/select.do")
    public String Select(HttpServletRequest request, HttpServletResponse response){
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pagenumber = Integer.parseInt(request.getParameter("pageNumber"));
        Integer count = expressService.count();
        List<Express> expressList;
        expressList = expressService.selectPart(pagenumber, offset);
        List<NewExpress> newExpressList = new ArrayList<>();
        ToNewExpress toNewExpress = new ToNewExpress();
        NewExpress newExpress = null;
        for (Express express: expressList) {
             newExpress = toNewExpress.convertExpress(express);
             newExpressList.add(newExpress);
        }
//        for (Express express:expressList) {
//            System.out.println(express.getExpress_id());
//        }
        ResultData resultData = new ResultData();
        resultData.setList(newExpressList);
        resultData.setTotal(count);
        return ToJsonUtil.toJson(resultData);
    }
    @ResponseBody("/admin/increase.do")
    public String Increase(HttpServletRequest request, HttpServletResponse response){
        String express_id = request.getParameter("code");
        String company = request.getParameter("company");
        String name = request.getParameter("username");
        String phone = request.getParameter("phonenumber");
        String courierPhone = request.getParameter("courierPhone");
        Random random = new Random();
        // 生成一个六位的随机数作为取件码
        int randomNumber = 100000 + random.nextInt(900000);
        while (expressService.SelectBYCode(randomNumber+"")!=null){
            randomNumber = 100000 + random.nextInt(900000);
        }
        String code = randomNumber + "";
        Express express = new Express();
        if (courierPhone!=null){
            express.setCourier_phone(courierPhone);
        }
        express.setPhone(phone);
        express.setExpress_id(express_id);
        express.setName(name);
        express.setCompany(company);
        express.setCode(code);
        Boolean flag = expressService.Increase(express);
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
    @ResponseBody("/wx/increaseExpress.do")
    public String IncreaseWxExpress(HttpServletRequest request, HttpServletResponse response){
        ExpressController expressController = new ExpressController();
        return expressController.Increase(request,response);
    }
    @ResponseBody("/admin/SelectById.do")
    public String SelectById(HttpServletRequest request, HttpServletResponse response){
        String express_id = request.getParameter("code");
        Express express = expressService.selectById(express_id);
        ToNewExpress toNewExpress = new ToNewExpress();
        NewExpress newExpress = toNewExpress.convertExpress(express);
        Message message = new Message();
        if (express!=null){
            message.setResult("查找成功");
            message.setData(newExpress);
            message.setStatus(1);
        }
        else {
            message.setResult("查找失败");
            message.setStatus(0);
        }
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/admin/update.do")
    public String Update(HttpServletRequest request, HttpServletResponse response){
        String express_id = request.getParameter("code");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String phone = request.getParameter("phonenumber");
        int status = Integer.parseInt(request.getParameter("status"));
        Express express = new Express();
        express.setCompany(company);
        express.setName(username);
        express.setPhone(phone);
        express.setStatus(status);
        Boolean flag = expressService.Update(express, express_id);
        Message message = new Message();
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
    @ResponseBody("/admin/delete.do")
    public String Delete(HttpServletRequest request, HttpServletResponse response){
        String express_id = request.getParameter("code");
        Boolean flag = expressService.delete(express_id);
        Message message = new Message();
        if (flag){
            message.setStatus(1);
            message.setResult("删除成功");
        }
        else {
            message.setStatus(0);
            message.setResult("删除失败");

        }
        return ToJsonUtil.toJson(message);

    }
    @ResponseBody("/wx/SelectBYPhone.do")
    public String SelectExpressByPhone(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("phone");
        List<Express> list = expressService.SelectByPhone(user.getPhone(),1);
        List<NewExpress> newExpressList = new ArrayList<>();
        for (Express express:list) {
            ToNewExpress toNewExpress = new ToNewExpress();
            NewExpress newExpress = toNewExpress.convertExpress(express);
            newExpressList.add(newExpress);
        }
        Message message = new Message();
        if (!(list.isEmpty())){
            message.setData(newExpressList);
            message.setStatus(1);
        }
        else {
            message.setStatus(0);
            message.setResult("没有您的快递信息");
        }
        return ToJsonUtil.toJson(message);
    }

}
