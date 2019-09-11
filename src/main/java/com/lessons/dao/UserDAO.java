package com.lessons.dao;

import com.lessons.model.Role;
import com.lessons.model.User;
import com.lessons.util.Constants;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements DAO<User> {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public void create(User user) {
        try {
            connection = DriverManager.getConnection(Constants.DATABASE_URL, Constants.DATABASE_USERNAME, Constants.DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(PostQueries.INSERT.getQuery());

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setInt(3, user.getRoleId());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try {
            connection = DriverManager.getConnection(Constants.DATABASE_URL, Constants.DATABASE_USERNAME, Constants.DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(PostQueries.SELECT.getQuery());

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("role_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public boolean isUserExistByUsernameAndPassword(String username,
                                                    String password) {
        System.out.println(username);
        System.out.println(password);
        boolean result = false;
        Connection connection = Connector.createConnection();
        try {
            preparedStatement = connection.prepareStatement(UserQueries.SELECT_BY_USERNAME_AND_PASSWORD.getQuery());

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            result = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
//                connection.close();
                Connector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Role getRoleByUsernameAndPassword(String username,
                                             String password) {
        Role role = Role.UNKNOWN;
        try {
//            connection = DriverManager.getConnection(Constants.DATABASE_URL, Constants.DATABASE_USERNAME, Constants.DATABASE_PASSWORD);
            Connection connection = Connector.createConnection();
            preparedStatement = connection.prepareStatement(UserQueries.SELECT_BY_USERNAME_AND_PASSWORD.getQuery());

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int result = resultSet.getInt("role_id");
                if (result == 1)
                    role = Role.ADMIN;
                else if (result == 2)
                    role = Role.USER;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
//                connection.close();
                Connector.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return role;
    }

    @Override
    public ArrayList<User> readAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer id) {

    }
}
