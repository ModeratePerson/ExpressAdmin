package com.express.service;

import com.express.bean.Couriers;

import java.util.List;

public interface CourierService {
    public List<Couriers> selectALL();
    public Couriers selectByPhone(String Courier_phone);
    public List<Couriers> selectPart(int limit, int offset);
    public Boolean Increase(Couriers couriers);
    public Boolean Update(Couriers couriers,String Courier_phone);
    public Boolean delete(String Courier_phone);
    public Integer count();
}
