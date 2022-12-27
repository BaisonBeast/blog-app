package com.blog.blogapp.security;

import com.blog.blogapp.entity.User;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "Email Id: ", 0));
        return user;
    }
}
