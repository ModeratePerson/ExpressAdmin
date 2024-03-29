package com.express.dao.Imple;

import com.express.bean.Express;
import com.express.dao.ExpressDao;
import com.express.utils.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpressDaoImple implements ExpressDao {
    private static final String SQL_ALL = "SELECT * FROM EXPRESS";
    private static final String SQL_ALL_BY_EXPRESS_ID = "SELECT * FROM EXPRESS WHERE EXPRESS_ID = ?" ;
    private static final String SQL_PART = "SELECT * FROM EXPRESS LIMIT ? OFFSET ?";
    private static final String SQL_BY_PHONE = "SELECT * FROM EXPRESS WHERE PHONE = ?";
    private static final String SQL_BY_CODE = "SELECT * FROM EXPRESS WHERE CODE = ?";
    private static final String INCREASE_EXPRESS = "INSERT INTO EXPRESS (COURIER_PHONE, EXPRESS_ID, COMPANY, NAME, PHONE, CODE, IN_TIME, STATUS) VALUES (?,?,?,?,?,?,NOW(),0)";
    private static final String MODIFY_EXPRESS = "UPDATE EXPRESS SET COMPANY = ?, NAME = ?, PHONE = ?, STATUS = ? WHERE EXPRESS_ID = ?";
    private static final String DELETE_EXPRESS = "DELETE FROM EXPRESS WHERE EXPRESS_ID = ?";
    private static final String COUNT_EXPRESS = "SELECT COUNT(*) AS TOTAL FROM EXPRESS";
    private static final String DELETE_EXPRESS_BY_USERID = "DELETE FROM EXPRESS WHERE USER_ID = ?";
    @Override
    public List<Express> selectALL() {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Express express = null;
        List<Express> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String express_id = resultSet.getString("express_id");
                String company = resultSet.getString("company");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                int status = resultSet.getInt("status");
                String code = resultSet.getString("code");
                Timestamp time = resultSet.getTimestamp("time");
                express = new Express();
                express.setId(id);
                express.setExpress_id(express_id);
                express.setCompany(company);
                express.setName(name);
                express.setPhone(phone);
                express.setStatus(status);
                express.setCode(code);
                express.setTime(time);
                list.add(express);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Express selectById(String express_id) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Express express = null;
        try {
            statement = connection.prepareStatement(SQL_ALL_BY_EXPRESS_ID);
            statement.setString(1,express_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String company = resultSet.getString("company");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                int status = resultSet.getInt("status");
                express = new Express();
                express.setExpress_id(express_id);
                express.setCompany(company);
                express.setName(name);
                express.setPhone(phone);
                express.setStatus(status);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return express;
    }


    @Override
    public List<Express> selectPart(int limit, int offset) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Express express = null;
        List<Express> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_PART);
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String express_id = resultSet.getString("express_id");
                String company = resultSet.getString("company");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                int status = resultSet.getInt("status");
                String code = resultSet.getString("code");
                Timestamp time = resultSet.getTimestamp("time");
                Timestamp in_time = resultSet.getTimestamp("in_time");
                String Courier_phone = resultSet.getString("courier_phone");
                express = new Express();
                express.setId(id);
                express.setExpress_id(express_id);
                express.setCompany(company);
                express.setName(name);
                express.setPhone(phone);
                express.setStatus(status);
                express.setCode(code);
                express.setIn_time(in_time);
                express.setTime(time);
                express.setCourier_phone(Courier_phone);
                list.add(express);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Express SelectBYCode(String code) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Express express = null;
        try {
            statement = connection.prepareStatement(SQL_BY_CODE);
            statement.setString(1,code);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                express = new Express();
                express.setName(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return express;
    }

    @Override
    public Boolean Increase(Express express) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        boolean status = false;
        try {
            statement = connection.prepareStatement(INCREASE_EXPRESS);
            if (express.getCourier_phone()!=null){
                statement.setString(1,express.getCourier_phone());
            }
            statement.setString(5,express.getPhone());
            statement.setString(2,express.getExpress_id());
            statement.setString(3,express.getCompany());
            statement.setString(4,express.getName());
            statement.setString(6, express.getCode());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                status = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,null);
        }
        return status;
    }

    @Override
    public Boolean Update(Express express, String express_id) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        boolean status = false;
        try {
            statement = connection.prepareStatement(MODIFY_EXPRESS);
            statement.setString(1,express.getCompany());
            statement.setString(2,express.getName());
            statement.setString(3,express.getPhone());
            statement.setInt(4,express.getStatus());
            statement.setString(5,express_id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                status = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return status;
    }

    @Override
    public Boolean delete(String express_id) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        boolean status = false;
        try {
            statement = connection.prepareStatement(DELETE_EXPRESS);
            statement.setString(1,express_id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                status = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return status;
    }

    @Override
    public Integer count() {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(COUNT_EXPRESS);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return count;
    }
    @Override
    public Boolean DeleteBYUserId(int user_id){
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(DELETE_EXPRESS_BY_USERID);
            statement.setInt(1,user_id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                flag = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return flag;
    }

    @Override
    public List<Express> SelectByPhone(String phone,int status) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Express express = null;
        List<Express> list = new ArrayList<>();
        try {
//            status=0时只查询未取件的express,status=1查询全部express
            if (status == 0){
                String sql = SQL_BY_PHONE + "AND STATUS = 0";
                System.out.println(sql);
                statement = connection.prepareStatement(sql);
                statement.setString(1,phone);
                resultSet = statement.executeQuery();
            }
            else if (status == 1){
                statement = connection.prepareStatement(SQL_BY_PHONE);
                statement.setString(1,phone);
                resultSet = statement.executeQuery();
            }
            while (resultSet.next()){
                express = new Express();
                String company = resultSet.getString("company");
                String expressId = resultSet.getString("express_id");
                String courierPhone = resultSet.getString("Courier_phone");
                String code = resultSet.getString("code");
                int status1 = resultSet.getInt("status");
                Timestamp inTime = resultSet.getTimestamp("in_time");
                Timestamp time = resultSet.getTimestamp("time");
                express.setCompany(company);
                express.setExpress_id(expressId);
                express.setTime(time);
                express.setIn_time(inTime);
                express.setCode(code);
                express.setCourier_phone(courierPhone);
                express.setStatus(status1);
                list.add(express);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return list;
    }

}
