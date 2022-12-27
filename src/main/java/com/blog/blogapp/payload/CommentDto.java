package com.blog.blogapp.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Integer id;

    private String content;
}
