package com.example.web_truyen.controller;

import com.example.web_truyen.dto.CategoryDTO;
import com.example.web_truyen.dto.CountryDTO;
import com.example.web_truyen.entity.Category;
import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.category.CategoryFilterForm;
import com.example.web_truyen.service.category.ICategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<CategoryDTO> getAllCategory(CategoryFilterForm form)
    {
        List<Category> categories = categoryService.getAllCategory(form);
        return modelMapper.map(categories,new TypeToken<List<CategoryDTO>>(){}.getType());
    }

    @PostMapping("create")
    public Category createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

}
