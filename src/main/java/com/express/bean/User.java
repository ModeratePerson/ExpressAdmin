package com.express.bean;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String phone;
    private String code;
    private Date sign_time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private Date login_time;
    private List<Express> list;
//    验证码生成时间
    private Date generate_time;

    public Date getGenerate_time() {
        return generate_time;
    }

    public void setGenerate_time(Date generate_time) {
        this.generate_time = generate_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<Express> getList() {
        return list;
    }

    public void setList(List<Express> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
