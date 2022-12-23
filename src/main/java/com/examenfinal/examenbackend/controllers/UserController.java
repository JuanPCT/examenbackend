package com.examenfinal.examenbackend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenfinal.examenbackend.entities.Bill;
import com.examenfinal.examenbackend.entities.User;
import com.examenfinal.examenbackend.model.requests.BillRequest;
import com.examenfinal.examenbackend.model.responses.BillResponse;
import com.examenfinal.examenbackend.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}/bills")
    public List<Bill> getBillsbyUser(@PathVariable Integer id){
        List<Bill> bills = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            bills = user.get().getBill();
        }
        return bills;
    }

    @PostMapping("/{id}/bills")
    public BillResponse postBillbyUser(@PathVariable Integer id, @RequestBody BillRequest billRequest){
        BillResponse billResponse = new BillResponse();

        return billResponse;
    }
}
