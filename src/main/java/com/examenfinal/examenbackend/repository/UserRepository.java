package com.examenfinal.examenbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.examenfinal.examenbackend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
