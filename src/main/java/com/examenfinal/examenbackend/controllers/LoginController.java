package com.examenfinal.examenbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenfinal.examenbackend.model.requests.UserRequest;
import com.examenfinal.examenbackend.model.responses.UserResponse;
import com.examenfinal.examenbackend.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public UserResponse login(@RequestBody UserRequest userRequest){
        UserResponse userResponse = new UserResponse();

        return userResponse;
    }


}
