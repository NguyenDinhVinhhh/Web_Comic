package com.example.web_truyen.util;

import com.example.web_truyen.entity.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtUtils {
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000L; // 1 ngày
    private static final String SECRET_KEY = "VTIACADEMY";

    // Tạo JWT từ Authentication
    public static String generateJwt(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername()) // Lưu username vào token
                .setIssuedAt(new Date()) // Thời gian phát hành
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // Hết hạn
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // Ký với HS256
                .compact();
    }

    // Kiểm tra tính hợp lệ của JWT
    public static boolean validateJwt(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUsername(String jwt) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

}
