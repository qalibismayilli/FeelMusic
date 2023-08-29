package com.company.feelmusic.service;

import com.company.feelmusic.dto.request.SongRequestDto;
import com.company.feelmusic.dto.response.SongResponseDto;
import com.company.feelmusic.model.Feel;
import com.company.feelmusic.model.Song;
import com.company.feelmusic.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final CategoryService categoryService;


    public SongService(SongRepository songRepository, CategoryService categoryService) {
        this.songRepository = songRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public SongResponseDto save(SongRequestDto request) {
        Song song = new Song.Builder().name(request.getName())
                .singer(request.getSinger()).category(categoryService.getCategoryById(request.getCategoryId()))
                .voiceUrl(request.getVoiceUrl()).lyrics(request.getLyrics())
                .image(request.getImage()).feelContainers(new ArrayList<>())
                .build();

        final Song fromDb = songRepository.save(song);

        return new SongResponseDto(fromDb.getName(), fromDb.getSinger(),
                fromDb.getCategory().getName(), fromDb.getLyrics(), fromDb.getImage());

    }


}
