package com.express.service.Imple;

import com.express.bean.AdminUser;
import com.express.dao.AdminDao;
import com.express.dao.Imple.AdminDaoImple;
import com.express.service.AdminService;

import java.util.Date;

public class AdminServiceImple implements AdminService {

    @Override
    public AdminUser login(String username, String password) {
        AdminDao adminDao = new AdminDaoImple();
        return adminDao.login(username,password);
    }

    @Override
    public Boolean UpdateIpAndTime(String ip, Date date,String username) {
        AdminDao adminDao = new AdminDaoImple();
        return adminDao.UpdateIpAndTime(ip,date,username);
    }
}
