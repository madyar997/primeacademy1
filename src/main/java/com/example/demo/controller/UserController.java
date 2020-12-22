package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return service.create(user);
    }

    @GetMapping("/users")
    public Iterable<User> showAllUsers(){
        return service.getAll();
    }

    @GetMapping("/users/me")
    public User findUserByLogin(){
        return service.getCurrentUser();
    }
}
