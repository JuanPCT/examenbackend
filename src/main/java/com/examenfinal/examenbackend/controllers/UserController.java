package com.examenfinal.examenbackend.controllers;

import java.beans.Beans;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.examenfinal.examenbackend.repository.BillRepository;
import com.examenfinal.examenbackend.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BillRepository billRepository;

    @GetMapping("/{id}/bills")
    public List<Bill> getBillsbyUser(@PathVariable Integer id) {
        List<Bill> bills = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            bills = user.get().getBill();
        }
        return bills;
    }

    @PostMapping("/{id}/bills")
    public BillResponse postBillbyUser(@PathVariable Integer id, @RequestBody BillRequest billRequest)
            throws Exception {
        BillResponse billResponse = new BillResponse();
        Bill bill = new Bill();
        Date date = new Date();

        BeanUtils.copyProperties(billRequest, bill);
        bill.setDate_bill(date);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User usuario = user.get();
            List<Bill> bills = usuario.getBill();
            bills.add(bill);
            usuario.setBill(bills);
            userRepository.save(usuario);
            bill.setUser(usuario);
            bill = billRepository.save(bill);
            BeanUtils.copyProperties(bill, billResponse);
            billResponse.setUser(bill.getUser().getUsername());
            billResponse.setId(bill.getId());
            billResponse.setDate(date);
        } else {
            throw new Exception("El usuario no existe");
        }

        return billResponse;
    }

    @DeleteMapping("/{id}/bills/{bill_id}")
    public Bill deleteBillbyUser(@PathVariable Integer id, @PathVariable Integer bill_id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            List<Bill> bills = user.get().getBill();
            Bill bill1 = new Bill();
            boolean existe = false;
            for (Bill bill : bills) {
                if (bill.getId()==bill_id) {
                    existe = true;
                    bill1 = bill;
                }
            }
            if (existe) {
                billRepository.deleteById(bill_id);
                return bill1;
            } else {
                throw new Exception("Bill no existe");
            }
        }else{
            throw new Exception("User no existe");
        }

    }

    @GetMapping("/{id}/bills/{bill_id}")
    public Bill getBillbyUser(@PathVariable Integer id, @PathVariable Integer bill_id) throws Exception{
    Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            List<Bill> bills = user.get().getBill();
            Bill bill1 = new Bill();
            boolean existe = false;
            for (Bill bill : bills) {
                if (bill.getId()==bill_id) {
                    existe = true;
                    bill1 = bill;
                }
            }
            if (existe) {
                return bill1;
            } else {
                throw new Exception("Bill no existe");
            }
        }else{
            throw new Exception("User no existe");
        }
    }

    @GetMapping("/all") public List<User> getAll() {
        return userRepository.findAll();
    }
}
