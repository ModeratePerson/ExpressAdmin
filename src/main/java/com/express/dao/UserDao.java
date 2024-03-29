package com.express.dao;

import com.express.bean.Couriers;
import com.express.bean.User;

import java.util.Date;
import java.util.List;

public interface UserDao {
    public List<User> selectALL();
    public User selectByPhone(String phone);
    public List<User> selectPart(int limit, int offset);
    public Boolean Increase(User user);
    public Boolean Update(User user,String phone);
    public Boolean update_sign_time(String phone);
    public Boolean update_login_time(String phone);
    public Boolean update_code(String code,String phone);
    public Boolean delete(String phone);
    public Integer count();
    public Boolean update_generate_time(String phone);
}
