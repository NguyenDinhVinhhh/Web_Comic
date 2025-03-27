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
import java.util.Optional;

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

    @Override
    public void DeleteCategoryById(int id) {
        if (!categoryRepository.existsById(id))
        {
            throw new RuntimeException("vui long nhap id khac");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public void UpdateCategory(int id, CategoryDTO categoryDTO) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category with ID " + id + " not found");
        }

        // Kiểm tra tên có bị trùng với quốc gia khác không
        Optional<Category> existingCategory = categoryRepository.findByName(categoryDTO.getName());
        if (existingCategory.isPresent() && existingCategory.get().getId() != id) {
            throw new RuntimeException("category name '" + categoryDTO.getName() + "' already exists");
        }

        // Chuyển đổi DTO -> Entity
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setId(id);

        // Cập nhật quốc gia
        categoryRepository.save(category);
    }
}
