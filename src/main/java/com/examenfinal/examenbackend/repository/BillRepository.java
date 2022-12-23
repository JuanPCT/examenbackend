package com.examenfinal.examenbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examenfinal.examenbackend.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{
    
}
