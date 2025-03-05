package com.example.web_truyen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Tắt CSRF để tránh lỗi truy cập tài nguyên tĩnh
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/uploads/**").permitAll()  // ✅ Cho phép truy cập thư mục ảnh
//                        .requestMatchers("/api/v1/user").permitAll() // ✅ Mở API user
//                        .anyRequest().permitAll() // 🔒 Các API khác yêu cầu đăng nhập
//                )
//                .formLogin(login -> login.disable()) // ❌ Tắt form login mặc định
//                .httpBasic(httpBasic -> httpBasic.disable()); // ❌ Tắt xác thực HTTP Basic
//
//        return http.build();
//    }
}


