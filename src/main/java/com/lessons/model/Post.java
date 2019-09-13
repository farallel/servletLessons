package com.lessons.model;

import java.util.Objects;

public class Post {
    private Integer id;
    private String text;
    private Integer userId;
    private String author;

    public Post() {
    }

    public Post(String text, Integer userId) {
        this.text = text;
        this.userId = userId;
    }

    public Post(Integer id, String text, Integer userId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) &&
                text.equals(post.text) &&
                userId.equals(post.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, userId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author=" + userId +
                '}';
    }
}
