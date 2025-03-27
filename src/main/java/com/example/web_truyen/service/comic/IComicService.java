package com.example.web_truyen.service.comic;

import com.example.web_truyen.entity.Comic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IComicService {
    List<Comic> getAllComic();
    Comic createComic(Comic comic, MultipartFile coverImage) throws IOException;
}
