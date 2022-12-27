package com.blog.blogapp.repository;

import com.blog.blogapp.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments, Integer> {
}
