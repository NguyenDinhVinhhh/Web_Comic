package com.example.web_truyen.service.category;

import com.example.web_truyen.entity.Category;
import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.category.CategoryFilterForm;
import com.example.web_truyen.repository.ICategoryRepository;
import com.example.web_truyen.specification.CategorySpectification;
import com.example.web_truyen.specification.CountrySpeccification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategory(CategoryFilterForm form) {

        Specification<Category> where = CategorySpectification.buildwhere(form);
        return categoryRepository.findAll(where);
    }
}
