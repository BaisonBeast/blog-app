package com.blog.blogapp.impl;

import com.blog.blogapp.entity.Category;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.entity.User;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.payload.PostDto;
import com.blog.blogapp.payload.PostResponse;
import com.blog.blogapp.repository.CategoryRepo;
import com.blog.blogapp.repository.PostRepo;
import com.blog.blogapp.repository.UserRepo;
import com.blog.blogapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,  Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "UserId :", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", "Catergory Id: ", categoryId));
        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.name");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        Post newPost = postRepo.save(post);

        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post ", "PostId : ", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post ", "PostId : ", postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable p = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPost = pagePost.getContent();
        List<PostDto> postDtos = allPost.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "PostId : ", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category ", "Catergory Id: ", categoryId));
        List<Post> posts = postRepo.findByCategory(cat);
        List<PostDto> postDtos =  posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User ", "UserId : ", userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> searchResult = postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = searchResult.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
