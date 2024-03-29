package com.express.bean;

import java.sql.Timestamp;

public class Express {
    private int id;
    private String express_id;
    private String company;
    private int user_id ;
    private String name;
//    收件人手机号
    private String phone;
    private int status;
    private String code;
//    快递员手机号
    private String Courier_phone;
//    入库时间
    private Timestamp in_time;

    public Timestamp getIn_time() {
        return in_time;
    }

    public void setIn_time(Timestamp in_time) {
        this.in_time = in_time;
    }

    //    出库时间
    private Timestamp time;

    public Express(int id, String express_id, String company, int user_id, String name, String phone, int status, String code, String courier_phone, Timestamp time) {
        this.id = id;
        this.express_id = express_id;
        this.company = company;
        this.user_id = user_id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.code = code;
        this.Courier_phone = courier_phone;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpress_id() {
        return express_id;
    }

    public void setExpress_id(String express_id) {
        this.express_id = express_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourier_phone() {
        return Courier_phone;
    }

    public void setCourier_phone(String courier_phone) {
        Courier_phone = courier_phone;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Express() {
    }
}
