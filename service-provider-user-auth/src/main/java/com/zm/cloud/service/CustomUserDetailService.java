package com.zm.cloud.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return new SecurityUser(1L, "user", "password1", "role1");
        } else if ("admin".equals(username)) {
            return new SecurityUser(2L,"admin", "password2", "admin-role");
        } else {
            return null;
        }
    }
}
