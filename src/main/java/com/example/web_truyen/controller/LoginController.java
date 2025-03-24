package com.example.web_truyen.controller;

import com.example.web_truyen.dto.JwtResponse;
import com.example.web_truyen.entity.User;
import com.example.web_truyen.form.login.LoginForm;
import com.example.web_truyen.service.user.IUserService;
import com.example.web_truyen.util.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginForm form)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getUsername(),form.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtUtils.generateJwt(authentication);
        User user = userService.getUserByUsername(authentication.getName());
        JwtResponse response = modelMapper.map(user, JwtResponse.class);
        response.setToken(jwt);
        return ResponseEntity.ok(response);
    }
}
