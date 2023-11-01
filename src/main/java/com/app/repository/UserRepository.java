package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import com.app.model.User;
import java.util.Optional;
import java.util.List;


public interface UserRepository extends CrudRepository<User, Long>  {

    User saveUser(User user);
    
    Optional<User> findUserById(Long id);
    
    List<User> getAllUsers();
    
    User updateUser(User user);
    
    void deleteUser(Long id);
}
