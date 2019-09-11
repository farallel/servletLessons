package com.lessons.dao;

public enum UserQueries {
    SELECT_BY_USERNAME_AND_PASSWORD("SELECT * FROM users WHERE users.username = (?) AND users.password = (?)"),
    GET_ROLE_BY_USERNAME_AND_PASSWORD("SELECT * FROM users WHERE users.username = (?) AND users.password = (?) RETURNING role_id");

    private String query;

    UserQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
