package com.company.feelmusic.service;

import com.company.feelmusic.dto.request.SongRequestDto;
import com.company.feelmusic.dto.response.SongResponseDto;
import com.company.feelmusic.exception.GenericException;
import com.company.feelmusic.model.Category;
import com.company.feelmusic.model.Song;
import com.company.feelmusic.repository.SongRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .singer(request.getSinger()).category(categoryService.getCategoryByName(request.getCategoryName()))
                .voiceUrl(request.getVoiceUrl()).lyrics(request.getLyrics())
                .feelContainers(new ArrayList<>())
                .build();

        final Song fromDb = songRepository.save(song);

        return new SongResponseDto(fromDb.getName(), fromDb.getSinger(),
                fromDb.getCategory().getName(), fromDb.getLyrics(), fromDb.getImage());
    }

    @Transactional
    public SongResponseDto remove(String songId) {
        Song song = songRepository.findById(songId).orElseThrow();
        songRepository.delete(song);

        return new SongResponseDto(song.getName(), song.getSinger(),
                song.getCategory().getName(), song.getLyrics(), song.getImage());
    }


    private static List<SongResponseDto> compareToResponse(List<Song> songs) {
        return songs.stream().map(song -> new SongResponseDto(song.getName(), song.getSinger(),
                song.getCategory().getName(), song.getLyrics(), song.getImage())).toList();
    }

    public List<SongResponseDto> listSongs(Integer pageNo, Integer size) {
        List<Song> songs = songRepository.findAll(PageRequest.of(pageNo - 1, size)).getContent();
        return compareToResponse(songs);
    }

    public List<SongResponseDto> searchByName(String name) {
        List<Song> fromDb = Optional.of(songRepository.findAllByName(name))
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Song not found by given name"));

        return compareToResponse(fromDb);
    }

    public List<SongResponseDto> searchBySinger(String singer) {
        List<Song> fromDb = Optional.of(songRepository.findAllBySinger(singer))
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Song not found by given Singer"));
        return compareToResponse(fromDb);
    }

    public List<SongResponseDto> searchByCategory(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        return compareToResponse(songRepository.findAllByCategory(category));
    }

    protected Song findById(String songId){
        return songRepository.findById(songId)
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND,"Song not found by given id"));
    }


}
