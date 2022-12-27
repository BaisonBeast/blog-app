package com.blog.blogapp.services;

import com.blog.blogapp.payload.CommentDto;

import javax.persistence.criteria.CriteriaBuilder;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}
