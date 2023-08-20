package com.company.feelmusic.repository;

import com.company.feelmusic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<String, User>, JpaSpecificationExecutor<User> {
}
