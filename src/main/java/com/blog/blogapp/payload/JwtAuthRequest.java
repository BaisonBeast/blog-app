package com.blog.blogapp.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private  String username;
    private String password;
}
