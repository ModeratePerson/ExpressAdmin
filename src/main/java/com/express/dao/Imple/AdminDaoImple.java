package com.express.dao.Imple;

import com.express.bean.AdminUser;
import com.express.dao.AdminDao;
import com.express.utils.DruidUtil;

import java.sql.*;
import java.util.Date;

public class AdminDaoImple implements AdminDao {
    private static final String SQL_LOGIN = "SELECT * FROM ADMIN WHERE USERNAME = ? AND PASSWORD = ?";
    private static final String UPDATE_IP_AND_TIME = "UPDATE ADMIN SET IP_ADDRESS = ? , LOGIN_TIME = ? WHERE USERNAME = ?";
    @Override
    public AdminUser login(String username, String password) {
        Connection connection = DruidUtil.getConnection();
        AdminUser adminUser = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_LOGIN);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                adminUser = new AdminUser();
                adminUser.setUsername(username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement,resultSet);
        }
        return adminUser;
    }

    @Override
    public Boolean UpdateIpAndTime(String ip, Date date,String username) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_IP_AND_TIME);
            statement.setString(1, ip);
            Timestamp timestamp = new Timestamp(date.getTime());
            statement.setTimestamp(2, timestamp);
            statement.setString(3,username);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidUtil.close(connection,statement, null);
        }

    }
}
