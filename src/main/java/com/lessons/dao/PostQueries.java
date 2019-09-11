package com.lessons.dao;

public enum PostQueries {
    INSERT("INSERT INTO posts (id, text, user_id) VALUES (DEFAULT, (?), (?)"),
    SELECT("SELECT * FROM posts WHERE posts.id = (?)"),
    UPDATE(""),
    DELETE(""),
    SELECT_ALL("SELECT * FROM posts");

    private String query;

    PostQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
