package com.example.springboot.todoThymeleaf.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String userName, String password){
        boolean isUserValid = userName.equalsIgnoreCase("vijaya");
        boolean isPasswordValid = password.equalsIgnoreCase("vijaya");
        return isUserValid && isPasswordValid;
    }
}
