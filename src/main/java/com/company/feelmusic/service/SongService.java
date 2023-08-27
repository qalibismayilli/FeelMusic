package com.company.feelmusic.service;

import com.company.feelmusic.dto.request.SongRequestDto;
import com.company.feelmusic.dto.response.SongResponseDto;
import com.company.feelmusic.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;


    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Optional<SongResponseDto> add(SongRequestDto request){
        return null;
    }
}
