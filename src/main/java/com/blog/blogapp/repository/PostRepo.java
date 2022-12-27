package com.blog.blogapp.repository;

import com.blog.blogapp.entity.Category;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
