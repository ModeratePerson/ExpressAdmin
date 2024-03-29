package com.express.bean;

import java.util.ArrayList;
import java.util.List;

public class ResultDataForUser {
    private List<User> list = new ArrayList<>();
    private int total;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
