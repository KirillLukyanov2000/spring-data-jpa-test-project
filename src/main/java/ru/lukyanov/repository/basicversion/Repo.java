package ru.lukyanov.repository.basicversion;

import java.util.Optional;

public interface Repo<T> {

    Optional<T> getById(Long id);

    void update(T entity);

}