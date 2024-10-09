package com.example.impl.security;

import com.example.entity.LoginUser;
import com.example.entity.SystemUser;
import com.example.feign.SystemUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SystemUserService systemUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserService.getUserByUserId(username);
//        if (!ObjectUtils.isEmpty(systemUser)) {
//            return org.springframework.security.core.userdetails.User.withUsername(username)
//                    .password(passwordEncoder.encode(systemUser.getPassword()))
//                    .authorities("ROLE_USER")
//                    .build();
//        }
        if (Objects.isNull(systemUser)) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<String> list = new ArrayList<>();
        return new LoginUser(systemUser, list);
    }
}
