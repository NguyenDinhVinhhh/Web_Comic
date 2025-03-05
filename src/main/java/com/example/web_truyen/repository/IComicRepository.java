package com.example.web_truyen.repository;

import com.example.web_truyen.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IComicRepository extends JpaRepository<Comic,Integer>, JpaSpecificationExecutor<Comic> {
}
