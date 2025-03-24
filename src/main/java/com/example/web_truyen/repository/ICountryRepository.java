package com.example.web_truyen.repository;

import com.example.web_truyen.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICountryRepository extends JpaRepository<Country,Integer>, JpaSpecificationExecutor<Country> {
    boolean existsByName(String name);
}
