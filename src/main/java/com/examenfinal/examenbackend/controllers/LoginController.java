package com.examenfinal.examenbackend.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenfinal.examenbackend.entities.User;
import com.examenfinal.examenbackend.model.requests.UserRequest;
import com.examenfinal.examenbackend.model.responses.LoginError;
import com.examenfinal.examenbackend.model.responses.UserResponse;
import com.examenfinal.examenbackend.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public UserResponse login(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        Optional<User> user = userRepository.findByUsername(userRequest.getUsername());
        System.out.println(user.get().getPass()+"///"+userRequest.getPass());
        if (user.isPresent()) {            
            if (user.get().getPass().equals(userRequest.getPass())) {
                BeanUtils.copyProperties(user, userResponse);
                userResponse.setEmail(user.get().getEmail());
                userResponse.setUsername(user.get().getUsername());
                userResponse.setMessage("Welcome");
                userResponse.setLogin(true);
            }else{
                userResponse.setLogin(false);
                userResponse.setMessage("Usuario o contraseña invalido");
            }
        } else {
            userResponse.setLogin(false);
            userResponse.setMessage("Usuario no existe");
        }
        return userResponse;
    }

}
