package com.examenfinal.examenbackend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examenfinal.examenbackend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
