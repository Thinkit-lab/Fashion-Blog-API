package com.devlon.fashionblog.repository;

import com.devlon.fashionblog.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    @Query(value = "update category set name = ?1, description = ?2 where category_id = ?3",
            nativeQuery = true)
    void updateCategory(String name, String description, Long id);

    @Query(value = "select * from category where name = ?1",
            nativeQuery = true)
    Category findByCategoryName(String categoryName);
}
