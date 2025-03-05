package com.example.web_truyen.form.user;

import com.example.web_truyen.entity.Role;
import org.springframework.web.multipart.MultipartFile;

public class CreateUserForm {
    private String username;
    private String password;
    private String email;
    private Role role;
    private MultipartFile image;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
