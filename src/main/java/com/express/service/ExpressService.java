package com.express.service;

import com.express.bean.Express;

import java.util.List;

public interface ExpressService {
    public List<Express> selectALL();
    public Express selectById(String express_id);
    public List<Express> selectPart(int limit, int offset);
    public Express SelectBYCode(String code);
    public Boolean Increase(Express express);
    public Boolean Update(Express express, String express_id);
    public Boolean delete(String express_id);
    public Integer count();
    public Boolean DeleteBYUserId(int user_id);
    public List<Express> SelectByPhone(String phone,int status);
}
