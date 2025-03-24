package com.example.web_truyen.dto;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;


public class CountryDTO {
    @Size(min = 8 , message = "chieu dai nho nhat la 8 ")
    private String name;

    public CountryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
