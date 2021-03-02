package com.tts.UsersAPI.controller;

import com.tts.UsersAPI.model.User;
import com.tts.UsersAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

//    @GetMapping("/users")
//    public List<User> getUsers(){
//        return (List<User>) userRepository.findAll();
//    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(value="state", required=false) String state){
        if (state != null) {
            return (List<User>) userRepository.findByStateOfResidence(state);
        }
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable(value="id") Long id){
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){

        userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id, @RequestBody User user){
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id){
        userRepository.deleteById(id);
    }

}
