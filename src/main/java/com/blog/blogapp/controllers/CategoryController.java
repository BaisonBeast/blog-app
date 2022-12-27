package com.blog.blogapp.controllers;

import com.blog.blogapp.payload.ApiResponse;
import com.blog.blogapp.payload.CategoryDto;
import com.blog.blogapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto,
            @PathVariable Integer catId
    ) {
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer catId) {
        categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        return  ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
        CategoryDto newCatDto = categoryService.getCategoryWithId(catId);
        return new ResponseEntity<CategoryDto>(newCatDto, HttpStatus.OK);
    }
}
