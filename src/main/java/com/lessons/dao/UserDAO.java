package com.lessons.dao;

import com.lessons.model.Role;
import com.lessons.model.User;
import com.lessons.util.Constants;
import com.sun.istack.internal.NotNull;

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
        boolean result = false;
        try {
            connection = Connector.createConnection();
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
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public User getUserByUsernameAndPassword(String username,
                                             String password) {
        User user = new User();
        try {
            connection = Connector.createConnection();
            preparedStatement = connection.prepareStatement(UserQueries.SELECT_BY_USERNAME_AND_PASSWORD.getQuery());

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
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
        user.setRole(getRoleById(user.getId()));
        return user;
    }

    public Role getRoleById(@NotNull Integer id) {
        Role role = null;
        try {
            connection = Connector.createConnection();
            preparedStatement = connection.prepareStatement(UserQueries.GET_ROLE_BY_ID.getQuery());

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String roleType = resultSet.getString("role");
                if (roleType.equals("ADMIN"))
                    role = Role.ADMIN;
                else if (roleType.equals("USER"))
                    role = Role.USER;
                else
                    role = Role.UNKNOWN;
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
