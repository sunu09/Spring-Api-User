package com.tts.UsersAPI.repository;

import com.tts.UsersAPI.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Object findByStateOfResidence(String state);

//    List<User> findByStateOfResidence(String state);
//
//    @Query("SELECT DISTINCT u.state FROM User u")
//    List<String> findByState();
//
//    List<User> findAll();
}
