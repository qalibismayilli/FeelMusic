package com.company.feelmusic.api;

import com.company.feelmusic.dto.request.SongRequestDto;
import com.company.feelmusic.dto.response.SongResponseDto;
import com.company.feelmusic.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/song")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/save")
    public ResponseEntity<SongResponseDto> save(@RequestBody SongRequestDto request) {
        return ResponseEntity.ok(songService.save(request));
    }

    @PostMapping("/remove")
    public ResponseEntity<SongResponseDto> remove(@RequestParam String songId) {
        return ResponseEntity.ok(songService.remove(songId));
    }

    @GetMapping("/listSongs")
    public ResponseEntity<List<SongResponseDto>> listSongs(@RequestParam Integer pageNo, @RequestParam Integer size) {
        return ResponseEntity.ok(songService.listSongs(pageNo, size));
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<SongResponseDto>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(songService.searchByCategory(name));
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<List<SongResponseDto>> searchByCategory(@RequestParam String categoryName) {
        return ResponseEntity.ok(songService.searchByCategory(categoryName));
    }

    @GetMapping("/searchBySinger")
    public ResponseEntity<List<SongResponseDto>> searchBySinger(@RequestParam String singerName) {
        return ResponseEntity.ok(songService.searchBySinger(singerName));
    }

}
