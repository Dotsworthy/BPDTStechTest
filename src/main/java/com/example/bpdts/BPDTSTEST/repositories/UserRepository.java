package com.example.bpdts.BPDTSTEST.repositories;

import com.example.bpdts.BPDTSTEST.models.User; // NEW
import org.springframework.data.jpa.repository.JpaRepository; // NEW
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
