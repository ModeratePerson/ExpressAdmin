package com.express.service.Imple;

import com.express.bean.Express;
import com.express.dao.ExpressDao;
import com.express.dao.Imple.ExpressDaoImple;
import com.express.service.ExpressService;

import java.util.List;

public class ExpressServiceImple implements ExpressService {
    private ExpressDao expressDao = new ExpressDaoImple();
    @Override
    public List<Express> selectALL() {
        return expressDao.selectALL();
    }

    @Override
    public Express selectById(String express_id) {
        return expressDao.selectById(express_id);
    }

    @Override
    public List<Express> selectPart(int limit, int offset) {
        return expressDao.selectPart(limit,offset);
    }

    @Override
    public Express SelectBYCode(String code) {
        return expressDao.SelectBYCode(code);
    }

    @Override
    public Boolean Increase(Express express) {
        return expressDao.Increase(express);
    }

    @Override
    public Boolean Update(Express express, String express_id) {
        return expressDao.Update(express,express_id);
    }

    @Override
    public Boolean delete(String express_id) {
        return expressDao.delete(express_id);
    }

    @Override
    public Integer count() {
        return expressDao.count();
    }

    @Override
    public Boolean DeleteBYUserId(int user_id) {
        return expressDao.DeleteBYUserId(user_id);
    }

    @Override
    public List<Express> SelectByPhone(String phone,int status) {
        return expressDao.SelectByPhone(phone,status);
    }
}
