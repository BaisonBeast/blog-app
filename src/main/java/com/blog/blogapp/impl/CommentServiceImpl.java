package com.blog.blogapp.impl;

import com.blog.blogapp.entity.Comments;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.payload.CommentDto;
import com.blog.blogapp.payload.PostResponse;
import com.blog.blogapp.repository.CommentRepo;
import com.blog.blogapp.repository.PostRepo;
import com.blog.blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id: ", postId));

        Comments comment = modelMapper.map(commentDto, Comments.class);

        comment.setPost(post);

        Comments savedComment = commentRepo.save(comment);

        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comments comment = commentRepo.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
        commentRepo.delete(comment);
    }
}
