package com.app.repository;
import com.app.model.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CrudRepository extends JpaRepository<User, Long> {
}
