package com.express.bean;

import javax.xml.crypto.Data;
import java.util.Date;

//快递员
public class Couriers {
    private String name;
    private String Courier_phone;
    private String id;
//    身份证号
    private String credit_card;
//    注册时间
    private Date sign_time;
//    上次登录时间
    private Date login_time;

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public Date getSign_time() {
        return sign_time;
    }

    public void setSign_time(Date sign_time) {
        this.sign_time = sign_time;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    //    派件数
    private Integer number;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourier_phone() {
        return Courier_phone;
    }

    public void setCourier_phone(String courier_phone) {
        Courier_phone = courier_phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
