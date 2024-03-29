package com.express.dao.Imple;

import com.express.bean.Couriers;
import com.express.bean.User;
import com.express.dao.UserDao;
import com.express.utils.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImple implements UserDao {
    private static final String SQL_ALL = "SELECT * FROM USER";
    private static final String SQL_ALL_BY_PHONE = "SELECT * FROM USER WHERE PHONE = ?" ;
    private static final String SQL_PART = "SELECT * FROM USER LIMIT ? OFFSET ?";
    private static final String INCREASE_USER = "INSERT INTO USER (NAME, PHONE) VALUES (?,?)";
    private static final String MODIFY_USER = "UPDATE USER SET NAME = ? WHERE PHONE = ?";
    private static final String UPDATE_USER_CODE = "UPDATE USER SET CODE = ? WHERE PHONE = ?";
    private static final String UPDATE_SIGN_TIME = "UPDATE USER SET SIGN_TIME = NOW() WHERE PHONE = ?";
    private static final String UPDATE_LOGIN_TIME = "UPDATE USER SET LOGIN_TIME = NOW() WHERE PHONE = ?";
    private static final String DELETE_USER = "DELETE FROM USER WHERE PHONE = ?";
    private static final String COUNT_USER = "SELECT COUNT(*) AS TOTAL FROM USER";
    private static final String UPDATE_GENERATE_TIME = "UPDATE USER SET GENERATE_TIME = NOW() WHERE PHONE = ?";
    @Override
    public List<User> selectALL() {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<User> list = new ArrayList<>();
        User user = null;
        try {
             statement = connection.prepareStatement(SQL_ALL);
             resultSet = statement.executeQuery();
             while (resultSet.next()){
                 int id = resultSet.getInt("id");
                 String name = resultSet.getString("name");
                 String phone = resultSet.getString("phone");
                 Date sign_time = resultSet.getTimestamp("sign_time");
                 Date login_time = resultSet.getTimestamp("login_time");
                 user = new User();
                 user.setId(id);
                 user.setName(name);
                 user.setPhone(phone);
                 user.setSign_time(sign_time);
                 user.setLogin_time(login_time);
                 list.add(user);
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
    public User selectByPhone(String phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_ALL_BY_PHONE);
            statement.setString(1,phone);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                user = new User();
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                Date generateTime = resultSet.getTimestamp("generate_time");
                user.setId(id);
                user.setPhone(phone);
                user.setName(name);
                user.setCode(code);
                user.setGenerate_time(generateTime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return user;
    }

    @Override
    public List<User> selectPart(int limit, int offset) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<User> list = new ArrayList<>();
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_PART);
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                Date sign_time = resultSet.getTimestamp("sign_time");
                Date login_time = resultSet.getTimestamp("login_time");
                user = new User();
                user.setId(id);
                user.setName(name);
                user.setPhone(phone);
                user.setSign_time(sign_time);
                user.setLogin_time(login_time);
                list.add(user);
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
    public Boolean Increase(User user) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(INCREASE_USER);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPhone());
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
    public Boolean Update(User user, String phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(MODIFY_USER);
            statement.setString(1,user.getName());
            statement.setString(2,phone);
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
    public Boolean update_sign_time( String phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(UPDATE_SIGN_TIME);
            statement.setString(1,phone);
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
    public Boolean update_login_time( String phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(UPDATE_LOGIN_TIME);
            statement.setString(1,phone);
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
    public Boolean update_code(String code, String phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(UPDATE_USER_CODE);
            statement.setString(1,code);
            statement.setString(2,phone);
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
    public Boolean delete(String phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(DELETE_USER);
            statement.setString(1,phone);
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
        Couriers couriers = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(COUNT_USER);
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
    public Boolean update_generate_time(String phone) {
        Connection connection = DruidUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Boolean flag = false;
        try {
            statement = connection.prepareStatement(UPDATE_GENERATE_TIME);
            statement.setString(1,phone);
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
}
