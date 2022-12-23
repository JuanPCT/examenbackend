package com.examenfinal.examenbackend.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.examenfinal.examenbackend.repository.BillRepository;
import com.examenfinal.examenbackend.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BillRepository billRepository;

    @GetMapping("/{user}/bills")
    public List<Bill> getBillsbyUser(@PathVariable String user) {
        List<Bill> bills = new ArrayList<>();
        Optional<User> usuario = userRepository.findByUsername(user);
        if (usuario.isPresent()) {
            bills = usuario.get().getBill();
        }
        return bills;
    }

    @PostMapping("/{user}/bills")
	public Bill saveBill(@PathVariable String user, @RequestBody BillRequest billreq) throws Exception {
        Optional<User> usuario = userRepository.findByUsername(user);
		if(usuario.isPresent()) {
            Bill bill = new Bill();
            Date date = new Date();
            BeanUtils.copyProperties(billreq, bill);
            bill.setUser_id(usuario.get().getId());
            bill.setDate_bill(date);
            
			return billRepository.save(bill);
		}else{
            throw new Exception("El usuario no existe");            
        }
		
	}

    @DeleteMapping("/{user}/bills/{bill_id}")
    public Bill deleteBillbyUser(@PathVariable String user, @PathVariable Integer bill_id) throws Exception {
        Optional<User> usuario = userRepository.findByUsername(user);
        if (usuario.isPresent()) {
            List<Bill> bills = usuario.get().getBill();
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

    @GetMapping("/{user}/bills/{bill_id}")
    public Bill getBillbyUser(@PathVariable String user, @PathVariable Integer bill_id) throws Exception{
    Optional<User> usuario = userRepository.findByUsername(user);
        if (usuario.isPresent()) {
            List<Bill> bills = usuario.get().getBill();
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

    @GetMapping("/all") 
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/bills")
    public List<Bill> getBills(){
        return billRepository.findAll();
    }
}
