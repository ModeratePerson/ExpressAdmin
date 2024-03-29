package com.express.Test;

import com.express.bean.Express;
import com.express.dao.ExpressDao;
import com.express.dao.Imple.ExpressDaoImple;

import java.util.List;
import java.util.Scanner;

public class Test {
    private ExpressDao expressDao = new ExpressDaoImple();
    public void Test(){
//       List<Express> list = expressDao.selectALL();
//        for (Express express:list) {
//            String expressId = express.getExpress_id();
//            System.out.println(expressId);
//        }
       List<Express> list1 = expressDao.selectPart(3,5);
        for (Express express:list1) {
            String expressId = express.getExpress_id();
            System.out.println(expressId);
        }
//        Express express = new Express();
//        express.setExpress_id("13123897");
//        express.setCompany("顺丰速运");
//        express.setName("李女士");
//        express.setPhone("12435656212");
//        boolean flag = expressDao.Increase(express);
//        System.out.println(flag);
        Express express = new Express();
        express.setCompany("顺丰速运");
        express.setName("张女士");
        express.setPhone("12433286212");
        express.setStatus(0);
        Boolean flag = expressDao.Update(express, "Express3");
        System.out.println(flag);
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.Test();
    }
}
