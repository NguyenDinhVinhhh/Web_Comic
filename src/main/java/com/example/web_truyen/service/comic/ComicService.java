package com.example.web_truyen.service.comic;

import com.example.web_truyen.entity.Comic;
import com.example.web_truyen.repository.IComicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ComicService implements IComicService{
    @Autowired
    private IComicRepository  comicRepository;
    private static final String UPLOAD_DIR = "uploads/";
    @Override
    public List<Comic> getAllComic() {
        return comicRepository.findAll();
    }
    private static final Logger logger = LoggerFactory.getLogger(ComicService.class);
    public Comic createComic(Comic comic, MultipartFile coverImage) throws IOException {
        if (coverImage != null && !coverImage.isEmpty()) {
            // Đường dẫn thư mục uploads ngang hàng với src
            String uploadDir = System.getProperty("user.dir") + "/uploads";

            // Tạo thư mục nếu chưa có
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Đặt tên file duy nhất
            String fileName = UUID.randomUUID() + "_" + coverImage.getOriginalFilename();
            Path filePath = Path.of(uploadDir, fileName);

            // Dùng try-with-resources để đảm bảo InputStream được đóng
            try (InputStream inputStream = coverImage.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            // Kiểm tra xem file có được lưu không
            if (Files.exists(filePath)) {
                System.out.println("File đã lưu thành công: " + filePath.toString());
            } else {
                System.out.println("Lỗi khi lưu file!");
            }

            // Lưu đường dẫn ảnh vào database
            comic.setCoverImage(fileName);
        }

        return comicRepository.save(comic);
    }

}
