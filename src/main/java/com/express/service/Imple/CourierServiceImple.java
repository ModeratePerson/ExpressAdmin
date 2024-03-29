package com.express.service.Imple;

import com.express.bean.Couriers;
import com.express.dao.CourierDao;
import com.express.dao.Imple.CourierDaoImple;
import com.express.service.CourierService;

import java.util.List;

public class CourierServiceImple implements CourierService {
    private CourierDao courierDao = new CourierDaoImple();
    @Override
    public List<Couriers> selectALL() {
        return courierDao.selectALL();
    }

    @Override
    public Couriers selectByPhone(String Courier_phone) {
        return courierDao.selectByPhone(Courier_phone);
    }

    @Override
    public List<Couriers> selectPart(int limit, int offset) {
        return courierDao.selectPart(limit,offset);
    }

    @Override
    public Boolean Increase(Couriers couriers) {
        return courierDao.Increase(couriers);
    }

    @Override
    public Boolean Update(Couriers couriers, String Courier_phone) {
        return courierDao.Update(couriers,Courier_phone);
    }

    @Override
    public Boolean delete(String Courier_phone) {
        return courierDao.delete(Courier_phone);
    }

    @Override
    public Integer count() {
        return courierDao.count();
    }
}
