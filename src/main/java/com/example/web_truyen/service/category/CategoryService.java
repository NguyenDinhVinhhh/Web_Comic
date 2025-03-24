package com.example.web_truyen.service.category;

import com.example.web_truyen.dto.CategoryDTO;
import com.example.web_truyen.entity.Category;
import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.category.CategoryFilterForm;
import com.example.web_truyen.repository.ICategoryRepository;
import com.example.web_truyen.specification.CategorySpectification;
import com.example.web_truyen.specification.CountrySpeccification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Category> getAllCategory(CategoryFilterForm form) {

        Specification<Category> where = CategorySpectification.buildwhere(form);
        return categoryRepository.findAll(where);
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new RuntimeException("Category already exists!");
        }

        // Chuyển từ DTO -> Entity trước khi lưu vào database
        Category category = modelMapper.map(categoryDTO, Category.class);
        return categoryRepository.save(category); // Trả về Country đã được lưu
    }
}
