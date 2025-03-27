package com.example.web_truyen.controller;

import com.example.web_truyen.dto.ComicDTO;
import com.example.web_truyen.entity.Comic;
import com.example.web_truyen.service.comic.IComicService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/comics")
@CrossOrigin("*")
public class ComicController {
    @Autowired
    private IComicService comicService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    private List<ComicDTO> getAllComic()
    {
        List<Comic> comics = comicService.getAllComic();
        return modelMapper.map(comics, new TypeToken<List<ComicDTO>>(){}.getType());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComic(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("author") String author,
            @RequestParam("status") String status,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage
    ) {
        try {
            Comic comic = new Comic();
            comic.setTitle(title);
            comic.setDescription(description);
            comic.setAuthor(author);
            comic.setStatus(com.example.web_truyen.entity.Status.valueOf(status.toUpperCase()));

            Comic savedComic = comicService.createComic(comic, coverImage);
            return ResponseEntity.ok(savedComic);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Lỗi khi upload ảnh: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Trạng thái không hợp lệ!");
        }
    }
}
