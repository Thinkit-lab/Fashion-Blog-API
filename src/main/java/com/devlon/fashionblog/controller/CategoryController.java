package com.devlon.fashionblog.controller;

import com.devlon.fashionblog.dto.CategoryDto;
import com.devlon.fashionblog.entity.Category;
import com.devlon.fashionblog.services.implementation.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fashion-blog/")
@Log4j2
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        log.info("Endpoint has been hit");
        CategoryDto response = categoryService.createCategory(categoryDto);
        log.info("Category created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/category")
    public ResponseEntity<?> viewCategories() {
        log.info("Endpoint has been hit");
        List<CategoryDto> response = categoryService.getAllCategories();
        log.info("successfully");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        log.info("Endpoint has been hit");
        categoryService.deleteCategory(id);
        log.info("Category deleted successfully");
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.FOUND);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<?> editCategory(@RequestBody @Valid CategoryDto categoryDto,
                                          @PathVariable Long id) {
        log.info("Endpoint has been hit");
        categoryService.editCategory(categoryDto, id);
        log.info("Category edited successfully");
        return new ResponseEntity<>("Successful", HttpStatus.CREATED);
    }
}
