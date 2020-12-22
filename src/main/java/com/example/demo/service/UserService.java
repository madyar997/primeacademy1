package com.example.demo.service;
import com.example.demo.model.Task;
import com.example.demo.model.User;

public interface UserService  {
    User create(User user);
    User getCurrentUser();
    Iterable<User> getAll();
}
