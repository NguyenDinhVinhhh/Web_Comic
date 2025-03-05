package com.example.web_truyen.repository;

import com.example.web_truyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
}
