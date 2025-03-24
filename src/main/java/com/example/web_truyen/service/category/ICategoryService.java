package com.example.web_truyen.service.category;

import com.example.web_truyen.dto.CategoryDTO;
import com.example.web_truyen.entity.Category;
import com.example.web_truyen.form.category.CategoryFilterForm;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory(CategoryFilterForm form);
    Category createCategory(CategoryDTO categoryDTO);
}
