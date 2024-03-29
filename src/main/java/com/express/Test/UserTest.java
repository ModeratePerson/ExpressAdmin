package com.express.Test;

import com.express.bean.User;
import com.express.dao.Imple.UserDaoImple;
import com.express.dao.UserDao;

import java.util.List;

public class UserTest {
    private UserDao userDao = new UserDaoImple();
    public void test(){
        Integer count = userDao.count();
        System.out.println(count);
    }
    public static void main(String[] args) {
        UserTest userTest = new UserTest();
        userTest.test();
    }
}
