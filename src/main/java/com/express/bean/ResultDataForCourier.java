package com.express.bean;

import java.util.ArrayList;
import java.util.List;

public class ResultDataForCourier {
    private List<Couriers> list = new ArrayList<>();
    private int total;

    public List<Couriers> getCouriersList() {
        return list;
    }

    public void setCouriersList(List<Couriers> couriersList) {
        this.list = couriersList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
