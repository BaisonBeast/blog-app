package com.blog.blogapp.services;

import com.blog.blogapp.payload.CategoryDto;
import java.util.List;
public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    void deleteCategory(Integer categoryId);
    CategoryDto getCategoryWithId(Integer categoryId);
    List<CategoryDto> getAllCategory();
}
