package com.example.demo.controller;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class TaskController {
    @Autowired
    UserService service;

    @Autowired
    private TaskRepository taskRepository;



    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        Long userId = service.getCurrentUser().getId();
        task.setUser_id(userId);
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAll(){
        Long userId = service.getCurrentUser().getId();
        return taskRepository.findAllByUserId(userId);
    }

    @GetMapping("/tasks/{id}")
    public Task getById(@PathVariable Long id){
        Long userId = service.getCurrentUser().getId();
        return taskRepository.findByUserId(userId, id);
    }

    @PutMapping("/tasks/{id}")
    public Task update(@PathVariable Long id,
                       @RequestBody Task task){
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}")
    public void patchMethod(@PathVariable Long id,
                            @RequestBody Task task){
        if (task.isDone()){
            taskRepository.markAsDone(id);
        }
    }
    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable Long id){
            taskRepository.markAsDone(id);
    }

}