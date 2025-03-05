package com.example.web_truyen.repository;

import com.example.web_truyen.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICategoryRepository extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {
}
