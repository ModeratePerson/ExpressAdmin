package com.express.dao.Imple;

import com.express.bean.Couriers;
import com.express.dao.CourierDao;
import com.express.utils.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourierDaoImple implements CourierDao {
    private static final String SQL_ALL = "SELECT * FROM COURIERS";
    private static final String SQL_ALL_BY_COURIER_PHONE = "SELECT * FROM COURIERS WHERE COURIER_PHONE = ?" ;
    private static final String SQL_PART = "SELECT * FROM COURIERS LIMIT ? OFFSET ?";
    private static final String INCREASE_COURIER = "INSERT INTO COURIERS (NAME, COURIER_PHONE, CREDIT_CARD) VALUES (?,?,?)";
    private static final String MODIFY_COURIER = "UPDATE COURIERS SET NAME = ?, CREDIT_CARD = ? WHERE COURIER_PHONE = ?";
    private static final String DELETE_COURIER = "DELETE FROM COURIERS WHERE COURIER_PHONE = ?";
    private static final String COUNT_COURIER = "SELECT COUNT(*) AS TOTAL FROM COURIERS";

    @Override
    public List<Couriers> selectALL() {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Couriers couriers = null;
        List<Couriers> couriersList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String courier_phone = resultSet.getString("courier_phone");
                String creditCard = resultSet.getString("credit_card");
                int number = resultSet.getInt("number");
                Date signTime = resultSet.getTimestamp("sign_time");
                Date login_time = resultSet.getTimestamp("login_time");
                couriers = new Couriers();
                couriers.setId(id);
                couriers.setName(name);
                couriers.setCourier_phone(courier_phone);
                couriers.setCredit_card(creditCard);
                couriers.setNumber(number);
                couriers.setSign_time(signTime);
                couriers.setLogin_time(login_time);
                couriersList.add(couriers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return couriersList;
    }

    @Override
    public Couriers selectByPhone(String Courier_phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Couriers couriers = null;
        try {
            statement = connection.prepareStatement(SQL_ALL_BY_COURIER_PHONE);
            statement.setString(1,Courier_phone);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                couriers = new Couriers();
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String creditCard = resultSet.getString("credit_card");
                int number = resultSet.getInt("number");
                Date signTime = resultSet.getTimestamp("sign_time");
                Date login_time = resultSet.getTimestamp("login_time");
                couriers.setId(id);
                couriers.setName(name);
                couriers.setCourier_phone(Courier_phone);
                couriers.setCredit_card(creditCard);
                couriers.setNumber(number);
                couriers.setSign_time(signTime);
                couriers.setLogin_time(login_time);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return couriers;
    }

    @Override
    public List<Couriers> selectPart(int limit, int offset) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Couriers couriers = null;
        List<Couriers> couriersList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_PART);
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String courier_phone = resultSet.getString("courier_phone");
                String creditCard = resultSet.getString("credit_card");
                int number = resultSet.getInt("number");
                Date signTime = resultSet.getTimestamp("sign_time");
                Date login_time = resultSet.getTimestamp("login_time");
                couriers = new Couriers();
                couriers.setId(id);
                couriers.setName(name);
                couriers.setCourier_phone(courier_phone);
                couriers.setCredit_card(creditCard);
                couriers.setNumber(number);
                couriers.setSign_time(signTime);
                couriers.setLogin_time(login_time);
                couriersList.add(couriers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return couriersList;
    }

    @Override
    public Boolean Increase(Couriers couriers) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(INCREASE_COURIER);
            statement.setString(1,couriers.getName());
            statement.setString(2,couriers.getCourier_phone());
            statement.setString(3,couriers.getCredit_card());
            int result = statement.executeUpdate();
            if (result>0){
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
    public Boolean Update(Couriers couriers, String Courier_phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(MODIFY_COURIER);
            statement.setString(1,couriers.getName());
            statement.setString(2,couriers.getCredit_card());
            statement.setString(3,Courier_phone);
            int result = statement.executeUpdate();
            if (result>0){
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
    public Boolean delete(String Courier_phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(DELETE_COURIER);
            statement.setString(1,Courier_phone);
            int result = statement.executeUpdate();
            if (result>0){
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
    public Integer count() {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(COUNT_COURIER);
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
}
