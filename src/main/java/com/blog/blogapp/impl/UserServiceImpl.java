package com.blog.blogapp.impl;

import com.blog.blogapp.entity.User;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.payload.UserDto;
import com.blog.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private com.blog.blogapp.repository.UserRepo UserRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = UserRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = UserRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User savedUser = UserRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = UserRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = UserRepo.findAll();
        List<UserDto> userDtos= users.stream()
                .map(user->userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = UserRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        UserRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);

    }

    private UserDto userToDto(User user) {
        return modelMapper.map(user ,UserDto.class);

    }
}
