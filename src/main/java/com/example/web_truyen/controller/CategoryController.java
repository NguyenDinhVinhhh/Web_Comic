package com.example.web_truyen.controller;

import com.example.web_truyen.dto.CategoryDTO;
import com.example.web_truyen.entity.Category;
import com.example.web_truyen.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CategoryDTO> getAllCategory()
    {
        List<Category> categories = categoryService.getAllCategory();
        return modelMapper.map(categories,new TypeToken<List<CategoryDTO>>(){}.getType());
    }
}
