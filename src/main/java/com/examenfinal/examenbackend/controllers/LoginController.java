package com.examenfinal.examenbackend.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenfinal.examenbackend.entities.User;
import com.examenfinal.examenbackend.model.requests.UserRequest;
import com.examenfinal.examenbackend.model.responses.UserResponse;
import com.examenfinal.examenbackend.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
                String token = getJWTToken(userResponse.getUsername());
                userResponse.setToken(token);
                
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

    private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
