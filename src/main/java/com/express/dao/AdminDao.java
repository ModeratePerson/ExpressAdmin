package com.express.dao;

import com.express.bean.AdminUser;

import java.util.Date;

public interface AdminDao {
    public AdminUser login(String username, String password);
    public Boolean UpdateIpAndTime(String ip, Date date,String username);
}
