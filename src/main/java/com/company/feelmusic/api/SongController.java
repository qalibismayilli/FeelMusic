package com.company.feelmusic.api;

import com.company.feelmusic.dto.request.SongRequestDto;
import com.company.feelmusic.dto.response.SongResponseDto;
import com.company.feelmusic.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/song")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/save")
    public ResponseEntity<SongResponseDto> save(@RequestBody SongRequestDto request){
        return ResponseEntity.ok(songService.save(request));
    }

}
