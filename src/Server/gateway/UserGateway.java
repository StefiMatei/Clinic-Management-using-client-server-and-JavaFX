package Server.gateway;

import Server.DatabaseConnection;
import Server.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by matei on 6/26/2017.
 */
public class UserGateway {
    public User searchUser(User user) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "select* from user u join role r on u.userId = r.roleId";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("password").equals(user.getPassword()) && resultSet.getString("userName").equals(user.getUsername())) {
                user.setUserId(resultSet.getInt("userId"));
                user.setRoleId(resultSet.getInt("roleId"));
            }

        }
        connection.commit();
        connection.close();
        return user;
    }

    public boolean existUsername(String username) throws SQLException {
        boolean exists = false;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * from user";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            if (username.equals(resultSet.getString("userName")))
                exists = true;
        }
        connection.close();
        return exists;
    }

    public boolean loginAdmin(String userName, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String databaseUserName = "";
        String databasePassword = "";
        boolean logged;
        String sql = "SELECT * from User where username=? && password=? && roleId=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setInt(3, 1);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            databaseUserName = resultSet.getString("username");
            databasePassword = resultSet.getString("password");
        }
        if (userName.equals(databaseUserName) && password.equals(databasePassword)) {
            logged = true;
        } else {
            logged = false;
        }
        return logged;
    }

    public boolean loginDoctor(String userName, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String databaseUserName = "";
        String databasePassword = "";
        boolean logged;
        String sql = "SELECT * from User where username=? && password=? && roleId=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setInt(3, 2);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            databaseUserName = resultSet.getString("username");
            databasePassword = resultSet.getString("password");
        }
        if (userName.equals(databaseUserName) && password.equals(databasePassword)) {
            logged = true;
        } else {
            logged = false;
        }
        return logged;
    }

    public boolean loginSecretary(String userName, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String databaseUserName = "";
        String databasePassword = "";
        boolean logged;
        String sql = "SELECT * from User where username=? && password=? && roleId=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setInt(3, 3);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            databaseUserName = resultSet.getString("username");
            databasePassword = resultSet.getString("password");
        }
        if (userName.equals(databaseUserName) && password.equals(databasePassword)) {
            logged = true;
        } else {
            logged = false;
        }
        return logged;
    }
}
