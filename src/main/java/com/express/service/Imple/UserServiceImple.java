package com.express.service.Imple;

import com.express.bean.Express;
import com.express.bean.User;
import com.express.dao.Imple.UserDaoImple;
import com.express.dao.UserDao;
import com.express.service.UserService;

import java.util.Date;
import java.util.List;

public class UserServiceImple implements UserService {
    private UserDao userDao = new UserDaoImple();
    @Override
    public List<User> selectALL() {
        return userDao.selectALL();
    }

    @Override
    public User selectByPhone(String phone) {
        return userDao.selectByPhone(phone);
    }

    @Override
    public List<User> selectPart(int limit, int offset) {
        return userDao.selectPart(limit,offset);
    }


    @Override
    public Boolean Increase(User user) {
        return userDao.Increase(user);
    }

    @Override
    public Boolean Update(User user, String phone) {
        return userDao.Update(user,phone);
    }

    @Override
    public Boolean update_sign_time( String phone) {
        return userDao.update_sign_time(phone);
    }

    @Override
    public Boolean update_login_time( String phone) {
        return userDao.update_login_time(phone);
    }

    @Override
    public Boolean update_code(String code, String phone) {
        return userDao.update_code(code,phone);
    }

    @Override
    public Boolean delete(String phone) {
        return userDao.delete(phone);
    }

    @Override
    public Integer count() {
        return userDao.count();
    }

    @Override
    public Boolean update_generate_time(String phone) {
        return userDao.update_generate_time(phone);
    }
}
