package com.express.Test;

import com.express.bean.Couriers;
import com.express.dao.CourierDao;
import com.express.dao.Imple.CourierDaoImple;

import java.util.List;

public class CourierTest {
    private CourierDao courierDao = new CourierDaoImple();
    public void test(){
//        Couriers couriers = courierDao.selectByPhone("555-555-5555");
//        System.out.println(couriers.getCredit_card());
//        List<Couriers> couriersList = courierDao.selectPart(2, 0);
//        for (Couriers couriers:couriersList) {
//            String creditCard = couriers.getCredit_card();
//            System.out.println(creditCard);
//        }
//        Couriers couriers = new Couriers();
//        couriers.setName("李强");
//        couriers.setCredit_card("194871294981221");
//        String Courier_phone = "123-456-7890";
//        Boolean update = courierDao.Update(couriers, Courier_phone);
//        System.out.println(update);
        Integer count = courierDao.count();
        System.out.println(count);
    }

    public static void main(String[] args) {
        CourierTest courierTest = new CourierTest();
        courierTest.test();
    }
}
