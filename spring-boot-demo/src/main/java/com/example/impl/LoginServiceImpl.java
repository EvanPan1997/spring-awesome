package com.example.impl;

import com.example.entity.LoginUser;
import com.example.entity.SystemUser;
import com.example.feign.LoginService;
import com.example.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过 AuthenticationManager 的 authenticate 方法来进行用户认证，
     * 认证成功的话要生成一个 JWT，放入响应中返回，并且为了让用户下回请求时需要通过 JWT 识别出具体的是
     * 哪个用户，我们需要把用户信息存入 redis，把 userId 作为 key
     */
    @Override
    public Map<String, String> login(SystemUser user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证失败
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("认证失败");
        }

        // 认证成功
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getUserId();
        String jwt = JwtUtils.generateToken(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        // 添加到redis
        redisTemplate.opsForValue().set("login:" + userId, loginUser);
        return map;
    }

    /**
     * 这里并不需要删除 SecurityContextHolder 中的信息，只需要删除 redis 中所存储的即可，
     * 因为在进行认证的时候，需要先在 SecurityContextHolder 中拿到信息后，再从 redis 中获取对应信息。
     * 每个请求都对应一个 SecurityContextHolder，所以删除 SecurityContextHolder 中的信息是无效的
     */
    @Override
    public void logout() {
        //获取 SecurityContextHolder 中的 用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getUserId();
        // 删除 redis 中的值
        redisTemplate.delete("login:" + userId);
    }
}
