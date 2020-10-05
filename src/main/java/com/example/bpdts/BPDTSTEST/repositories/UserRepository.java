package com.example.bpdts.BPDTSTEST.repositories;

import com.example.bpdts.BPDTSTEST.models.User; // NEW
import org.springframework.data.jpa.repository.JpaRepository; // NEW
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByCity(String city);

    List<User> findUserById(Long id);
}
