package org.example.springdatam1.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T,ID> {

    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    T update(ID id,T entity);
    void delete(ID id);



}
