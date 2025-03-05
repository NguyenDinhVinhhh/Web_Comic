package com.example.web_truyen.controller;

import com.example.web_truyen.dto.UserDTO;
import com.example.web_truyen.entity.User;
import com.example.web_truyen.form.user.CreateUserForm;
import com.example.web_truyen.form.user.UserFilterForm;
import com.example.web_truyen.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public List<UserDTO> getALlUser(UserFilterForm form)
    {
        List<User> users = userService.getAllUser(form);
        return modelMapper.map(users,new TypeToken<List<UserDTO>>(){}.getType());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createUser(@ModelAttribute CreateUserForm form) {
        userService.createUser(form);
        return ResponseEntity.ok("User created successfully!");
    }

}
