package com.example.impl.security;

import com.example.entity.SystemUser;
import com.example.service.SystemUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserService.getUserByUserId(username);
        if (!ObjectUtils.isEmpty(systemUser)) {
            return org.springframework.security.core.userdetails.User.withUsername(username)
                    .password(passwordEncoder.encode(systemUser.getPassword()))
                    .authorities("ROLE_USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
