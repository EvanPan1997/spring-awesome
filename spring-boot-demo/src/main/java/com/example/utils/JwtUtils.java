package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "test";
    private static final String PROJECT_NAME = "spring-awesome";

    private static final long TIME = 86400000;

    public static String generateToken(String userId) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + TIME);

        byte[] bytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
//        String encode = TextCodec.BASE64.encode(bytes);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuer("evan")
                .setIssuedAt(now)
                .setExpiration(exp)
                .claim("project", PROJECT_NAME) // 临时参数
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
