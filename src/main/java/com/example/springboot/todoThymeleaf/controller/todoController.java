package com.example.springboot.todoThymeleaf.controller;


import com.example.springboot.todoThymeleaf.Todo;
import com.example.springboot.todoThymeleaf.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class todoController {
    @RequestMapping   ("todo")
    public String todo(Model model){
        model.addAttribute("message","Hello World Spring Boot & Thyme Leaf App");
        return "todo";
    }

    public TodoService todoService;

    public todoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        String userName = getLoggedUserName(model);
        List<Todo> todos =todoService.findByUserName(userName);
        model.addAttribute("todos",todos);
        return "listTodo";
    }

    private String getLoggedUserName(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "add-todo" , method = RequestMethod.GET)//two-way binding
    public String addTodo(Model model){
        model.addAttribute("todo",new Todo(6,"chandini","",LocalDate.now().plusYears(1),false));
        return "add-todo";
    }
    @RequestMapping(value = "add-todo" , method = RequestMethod.POST)//two-way binding
    public String postTodo(@Valid @ModelAttribute("todo") Todo todo,BindingResult result,ModelMap model){
        if(result.hasErrors()){
            return "add-todo";
        }
        String username = getLoggedUserName(model);
        todoService.addTodo(username,todo.getDescription(), todo.getTargetDate(),false);
        return "redirect:list-todos";
    }
    @GetMapping(value = "delete-todos/{id}")//two-way binding
    public String deleteTodo(@PathVariable("id") int id){
        todoService.deleteById(id);
        return "redirect:/list-todos";
    }
    @GetMapping (value = "update-todos/{id}")//two-way binding
    public String showUpdateTodo(@PathVariable("id") int id,ModelMap model){
        Todo todo = todoService.findById(id);
        model.addAttribute("todo",todo);
        return "todo";

    }
    @RequestMapping(value = "update-todos/{id}" , method = RequestMethod.PUT)//two-way binding
    @ResponseBody
    public String updatePostTodo(@Valid @ModelAttribute("todo") Todo todo,BindingResult result){
        if(result.hasErrors()){
            return "add-todo";
        }
        System.out.println("Entered search");
          todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

}
