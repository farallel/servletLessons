package com.lessons.dao;

public enum PostQueries {
    INSERT("INSERT INTO posts (id, text, user_id) VALUES (DEFAULT, (?), (?))"),
    SELECT("SELECT * FROM posts WHERE posts.id = (?)"),
    UPDATE("UPDATE posts SET text = (?) WHERE posts.id = (?)"),
    DELETE("DELETE FROM posts WHERE posts.id = (?)"),
    SELECT_ALL("SELECT * FROM posts");

    private String query;

    PostQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
