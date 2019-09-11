package com.lessons.dao;

import java.util.ArrayList;

public interface DAO<Entity> {
    void create(Entity entity);
    Entity read(Integer id);
    ArrayList<Entity> readAll();
    void update(Entity entity);
    void delete(Integer id);
}
