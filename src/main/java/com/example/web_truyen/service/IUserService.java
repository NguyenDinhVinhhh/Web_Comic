package com.example.web_truyen.service;

import com.example.web_truyen.entity.User;
import com.example.web_truyen.form.user.CreateUserForm;
import com.example.web_truyen.form.user.UserFilterForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getAllUser(UserFilterForm form);
    void createUser(CreateUserForm form);

    User getUserByUsername(String username);
}
