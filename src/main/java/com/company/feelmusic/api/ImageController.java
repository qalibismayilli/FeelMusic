package com.company.feelmusic.api;

import com.company.feelmusic.dto.ImageModel;
import com.company.feelmusic.dto.response.ImageResponseDto;
import com.company.feelmusic.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/addImageToSong")
    public ResponseEntity<ImageResponseDto> addImageToSong(@RequestParam String imageId, @RequestParam String songId){
        return ResponseEntity.ok(imageService.addImageToSong(imageId, songId));
    }

    @PostMapping("/removeImage")
    public ResponseEntity<ImageResponseDto> removeImage(String imageId){
        return ResponseEntity.ok(imageService.removeImage(imageId));
    }

    @GetMapping("/getImagesBySondgId")
    public ResponseEntity<List<ImageResponseDto>> getImagesBySongId(String songId){
        return ResponseEntity.ok(imageService.getImagesBySongId(songId));
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<Map> uploadImage(ImageModel imageModel){
        return imageService.uploadImage(imageModel);
    }


}
