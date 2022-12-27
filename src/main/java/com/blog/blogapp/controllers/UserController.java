package com.blog.blogapp.controllers;

import com.blog.blogapp.payload.ApiResponse;
import com.blog.blogapp.payload.UserDto;
import com.blog.blogapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
      UserDto newUserDto = userService.createUser(userDto);
      return new ResponseEntity<UserDto>( newUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Integer userId,
           @Valid @RequestBody UserDto userDto
    ) {
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SDE')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return  ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        UserDto newUserDto = userService.getUserById(userId);
        return new ResponseEntity<UserDto>(newUserDto, HttpStatus.OK);
    }
}
