package com.example.web_truyen.repository;

import com.example.web_truyen.entity.Category;
import com.example.web_truyen.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {

    boolean existsByName(String name);
    Optional<Category> findByName(String name);
    boolean existsById(int id);
}
