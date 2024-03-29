package com.express.controller;

import com.express.bean.*;
import com.express.mvc.ResponseBody;
import com.express.service.CourierService;
import com.express.service.Imple.CourierServiceImple;
import com.express.utils.ToJsonUtil;
import com.express.utils.ToNewExpress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CourierController {
    private CourierService courierService = new CourierServiceImple();
    @ResponseBody("/admin/select_courier.do")
    public String select(HttpServletRequest request, HttpServletResponse response){
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pagenumber = Integer.parseInt(request.getParameter("pageNumber"));
        Integer count = courierService.count();
        List<Couriers> couriersList;
        couriersList = courierService.selectPart(pagenumber, offset);
        ResultDataForCourier resultData = new ResultDataForCourier();
        resultData.setCouriersList(couriersList);
        resultData.setTotal(count);
        System.out.println(ToJsonUtil.toJson(resultData));
        return ToJsonUtil.toJson(resultData);
    }
    @ResponseBody("/admin/increase_courier.do")
    public String increase(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String creditNumber = request.getParameter("credit_number");
        Couriers couriers = new Couriers();
        couriers.setCourier_phone(phone);
        couriers.setName(name);
        couriers.setCredit_card(creditNumber);
        Boolean flag = courierService.Increase(couriers);
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
    @ResponseBody("/admin/SelectByPhone.do")
    public String SelectByPhone(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        Couriers couriers = courierService.selectByPhone(phone);
        Message message = new Message();
        if (couriers.getName()!= null) {
            message.setData(couriers);
            message.setResult("查询成功");
            message.setStatus(1);
        }
        else {
            message.setStatus(0);
            message.setResult("查询失败");
        }
        return ToJsonUtil.toJson(message);
    }
    @ResponseBody("/admin/update_courier.do")
    public String update(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String creditNumber = request.getParameter("credit_number");
        String phone = request.getParameter("phone");
        Couriers couriers = new Couriers();
        couriers.setCourier_phone(phone);
        couriers.setCredit_card(creditNumber);
        couriers.setName(name);
        Message message = new Message();
        Boolean flag = courierService.Update(couriers, phone);
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
    @ResponseBody("/admin/delete_courier.do")
    public String delete(HttpServletRequest request, HttpServletResponse response){
        String phone = request.getParameter("phone");
        Boolean flag = courierService.delete(phone);
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

}
