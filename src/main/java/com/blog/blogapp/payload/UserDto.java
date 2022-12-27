package com.blog.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "User Name must be greater that 4 characters!!")
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "The password must be in the range of 3 - 10 chars")
    private String password;
    @NotEmpty
    private String about;

}
