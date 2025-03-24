package com.example.web_truyen.service.comic;

import com.example.web_truyen.entity.Comic;
import com.example.web_truyen.repository.IComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComicService implements IComicService{
    @Autowired
    private IComicRepository  comicRepository;
    @Override
    public List<Comic> getAllComic() {
        return comicRepository.findAll();
    }
}
