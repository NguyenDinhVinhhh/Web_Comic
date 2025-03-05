package com.example.web_truyen.service;

import com.example.web_truyen.entity.User;
import com.example.web_truyen.form.user.CreateUserForm;
import com.example.web_truyen.form.user.UserFilterForm;
import com.example.web_truyen.repository.IUserRepository;
import com.example.web_truyen.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;
    @Override
    public List<User> getAllUser(UserFilterForm form) {
        Specification<User> where = UserSpecification.buildwhere(form);
        return userRepository.findAll(where);
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void createUser(CreateUserForm form) {
        String uploadDir = "uploads/";
        File directory = new File(uploadDir);

        // Kiểm tra nếu thư mục chưa tồn tại thì tạo mới
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String imageUrl = null;

        if (form.getImage() != null && !form.getImage().isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + form.getImage().getOriginalFilename();
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(form.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                imageUrl = "/uploads/" + fileName; // Lưu đường dẫn ảnh
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi lưu ảnh!", e);
            }
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(form.getPassword());

        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(encodedPassword); // Lưu mật khẩu đã mã hóa
        user.setEmail(form.getEmail());
        user.setRole(form.getRole());
        user.setImageurl(imageUrl);

        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),Collections.emptyList());
    }
}
