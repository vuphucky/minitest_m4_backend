package com.codegym.service;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    void save(T t);
    void delete(Long id);
}
