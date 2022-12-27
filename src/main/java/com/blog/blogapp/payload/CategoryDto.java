package com.blog.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    @Size(min = 3, message = "The minimum size of the Tile is 3")
    private String categoryTitle;
    @NotBlank
    @Size(min = 8, message = "The minimum size of the Description is 8")
    private String categoryDescription;
}
