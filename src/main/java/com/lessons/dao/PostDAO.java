package com.lessons.dao;

import com.lessons.model.Post;
import com.lessons.util.Constants;

import java.sql.*;
import java.util.ArrayList;

public class PostDAO implements DAO<Post> {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public void create(Post post) {
        try {
            connection = Connector.createConnection();
            preparedStatement = connection.prepareStatement(PostQueries.INSERT.getQuery());

            preparedStatement.setString(1, post.getText());
            preparedStatement.setInt(2, post.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Post read(Integer id) {
        Post post = null;
        try {
            connection = Connector.createConnection();
            preparedStatement = connection.prepareStatement(PostQueries.SELECT.getQuery());

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                post = new Post(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getInt("user_id"));
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
        return post;
    }

    @Override
    public ArrayList<Post> readAll() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            connection = Connector.createConnection();
            preparedStatement = connection.prepareStatement(PostQueries.SELECT_ALL.getQuery());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("text"),
                        resultSet.getInt("user_id")
                ));
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
        return posts;
    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(Integer id) {
        try {
            connection = Connector.createConnection();
            preparedStatement = connection.prepareStatement(PostQueries.DELETE.getQuery());

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
