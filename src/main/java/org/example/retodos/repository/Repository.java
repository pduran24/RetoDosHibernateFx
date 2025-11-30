package org.example.retodos.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T findById(Integer id);
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
