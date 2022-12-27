package com.blog.blogapp.repository;

import com.blog.blogapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
