package com.example.springboot.todoThymeleaf.service;

import com.example.springboot.todoThymeleaf.Todo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List <Todo> todos = new ArrayList<>();
   private static int todoCount = 0;
    static {
        todos.add(new Todo(++todoCount,"anil","AWS Learning", LocalDate.now().plusYears(4),false));
        todos.add(new Todo(++todoCount,"vijaya","DevOps Learning", LocalDate.now().plusYears(2),false));
        todos.add(new Todo(++todoCount,"kartik","Gita Learning", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todoCount,"ashirya","Dance Learning", LocalDate.now().plusYears(1),false));
    }
    public List<Todo> findByUserName(String userName){
        Predicate<?super Todo> predicate= todo -> todo.getUserName().equalsIgnoreCase(userName);
        return todos.stream().filter(predicate).toList();
    }
    public void addTodo(String userName, String description, LocalDate targetDate, boolean done ){
        Todo todo = new Todo(++todoCount,userName,description,targetDate,done);
        todos.add(todo);
    }
    public void deleteById(int id){
        Predicate<?super Todo> predicate= todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }
    public Todo findById(int id){
        Predicate<?super Todo> predicate= todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }
    public void updateTodo(@Valid Todo todo){
        deleteById(todo.getId());
        //System.out.println("hi");
       todos.add(todo);
    }
}
