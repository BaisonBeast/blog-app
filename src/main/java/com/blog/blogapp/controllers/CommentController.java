package com.blog.blogapp.controllers;

import com.blog.blogapp.payload.ApiResponse;
import com.blog.blogapp.payload.CommentDto;
import com.blog.blogapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment (
            @RequestBody CommentDto commentDto,
            @PathVariable Integer postId
            ) {
        CommentDto commentDto1 = commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(commentDto1,HttpStatus.CREATED);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment (
            @PathVariable Integer commentId
    ) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully", true), HttpStatus.OK);
    }
}
