package com.blog.blogapp.services;
import com.blog.blogapp.payload.PostDto;
import com.blog.blogapp.payload.PostResponse;
import java.util.List;
public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);
}
