package com.company.feelmusic.repository;

import com.company.feelmusic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
    List<User> findAllByEmail(String email);
}
