package com.example.web_truyen.controller;

import com.example.web_truyen.dto.ComicDTO;
import com.example.web_truyen.entity.Comic;
import com.example.web_truyen.service.comic.IComicService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
