package com.devlon.fashionblog.services.implementation;

import com.devlon.fashionblog.dto.CategoryDto;
import com.devlon.fashionblog.entity.Category;
import com.devlon.fashionblog.exception.BadRequestException;
import com.devlon.fashionblog.exception.NotFoundException;
import com.devlon.fashionblog.repository.CategoryRepository;
import com.devlon.fashionblog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
//@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);

        try {
            Category createdCategory = categoryRepository.save(category);
            return mapToCategoryDto(createdCategory);
        } catch (BadRequestException e) {
            throw new BadRequestException("Unable to complete request");
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream().map(this::mapToCategoryDto).collect(Collectors.toList());
        } catch (NotFoundException e) {
            throw new NotFoundException("Category not found");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if(existingCategory.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        try {
            categoryRepository.delete(existingCategory.get());
        } catch (BadRequestException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public void editCategory(CategoryDto categoryDto, Long id) {
        try {
            categoryRepository.updateCategory(categoryDto.getName(), categoryDto.getDescription(), id);
        } catch (BadRequestException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category == null) {
            throw new NotFoundException("Category not found");
        }
        return category;
    }

    public CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
