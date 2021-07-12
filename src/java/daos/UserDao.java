/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UserDto;
import util.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author haudq
 */
public class UserDao implements Serializable {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private void closeConnection() throws Exception {
        if (resultSet != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public boolean checkUserId(String userId) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT [Id] FROM [dbo].[User] where UserID = ?");
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean activeAccount(String userID) throws Exception {
        boolean result;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareCall("Update [dbo].[User] set [Status] = ? where UserID = ?");
            preparedStatement.setString(1, "active");
            preparedStatement.setString(2, userID);
            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean createAccount(UserDto dto) throws Exception {
        boolean result;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareCall("INSERT INTO [dbo].[User] ([UserID], "
                    + " [Password], [Name],[Phone],[Address],[Role],[Status]) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, dto.getUserId());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.setString(3, dto.getName());
            preparedStatement.setString(4, dto.getPhone());
            preparedStatement.setString(5, dto.getAddress());
            preparedStatement.setString(6, dto.getRole());
            preparedStatement.setString(7, dto.getStatus());

            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public UserDto loginByGoogle(String email) throws Exception {
        UserDto result = new UserDto();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT [Id], [Role], [Name], [Status] FROM [dbo].[User] WHERE UserID = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getInt("Id"));
                result.setName(resultSet.getString("Name").trim());
                result.setRole(resultSet.getString("Role").trim());
                result.setStatus(resultSet.getString("Status").trim());
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public UserDto loginByUserIdAndPassword(String userId, String password) throws Exception {
        UserDto result = new UserDto();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT [Id], [Role], [Name], [Status] FROM [dbo].[User] WHERE UserID = ? and Password = ?");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getInt("Id"));
                result.setName(resultSet.getString("Name").trim());
                result.setRole(resultSet.getString("Role").trim());
                result.setStatus(resultSet.getString("Status").trim());
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
