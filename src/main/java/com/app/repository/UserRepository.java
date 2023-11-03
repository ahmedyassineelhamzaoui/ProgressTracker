package com.app.repository;
import com.app.model.User;
import java.util.Optional;
import java.util.List;


public interface UserRepository {


    <S extends User> S save(S entity);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    <S extends User> S update(S entity);
}
