package com.express.bean;

import java.util.ArrayList;
import java.util.List;

public class ResultData {
    public List<NewExpress> list = new ArrayList<>();
    public int total;

    public List<NewExpress> getList() {
        return list;
    }

    public void setList(List<NewExpress> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
