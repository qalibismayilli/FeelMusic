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

    @PostMapping("/admin/addImageToSong")
    public ResponseEntity<Void> addImageToSong(@RequestParam String imageId, @RequestParam String songId){
        imageService.addImageToSong(imageId, songId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/removeImage")
    public ResponseEntity<ImageResponseDto> removeImage(String imageId){
        return ResponseEntity.ok(imageService.removeImage(imageId));
    }

    @GetMapping("/getImagesBySongId")
    public ResponseEntity<ImageResponseDto> getImagesBySongId(String songId){
        return ResponseEntity.ok(imageService.getImageBySongId(songId));
    }

    @PostMapping("/admin/uploadImage")
    public ResponseEntity<Map> uploadImage(ImageModel imageModel){
        return imageService.uploadImage(imageModel);
    }


}
