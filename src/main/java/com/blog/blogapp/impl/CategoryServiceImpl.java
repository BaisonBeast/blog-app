package com.blog.blogapp.impl;

import com.blog.blogapp.entity.Category;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.payload.CategoryDto;
import com.blog.blogapp.repository.CategoryRepo;
import com.blog.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category cat = categoryRepo.save(category);
        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Catergory Id: ", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCat = categoryRepo.save(cat);

        return modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id: ", categoryId));
        categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategoryWithId(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Catergory", "Category Id: ", categoryId));

        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = category.stream().map((cat)->modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
