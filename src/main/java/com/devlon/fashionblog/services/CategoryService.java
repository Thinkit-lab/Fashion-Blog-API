package com.devlon.fashionblog.services;

import com.devlon.fashionblog.dto.CategoryDto;
import com.devlon.fashionblog.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Long id);

    void editCategory(CategoryDto categoryDto, Long id);

    Category getCategoryByName(String categoryName);

}
