package com.example.springboot.todoThymeleaf.Repository;

import com.example.springboot.todoThymeleaf.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
    public List<Todo> findByUserName(String userName);
}
