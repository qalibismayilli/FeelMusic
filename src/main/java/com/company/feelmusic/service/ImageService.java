package com.company.feelmusic.service;

import com.company.feelmusic.dto.ImageModel;
import com.company.feelmusic.dto.response.ImageResponseDto;
import com.company.feelmusic.exception.GenericException;
import com.company.feelmusic.model.Image;
import com.company.feelmusic.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;
    private final SongService songService;


    public ImageService(ImageRepository imageRepository, CloudinaryServiceImpl cloudinaryServiceImpl, SongService songService) {
        this.imageRepository = imageRepository;
        this.cloudinaryServiceImpl = cloudinaryServiceImpl;
        this.songService = songService;
    }

    protected ImageResponseDto convertToResponseDto(Image image) {
        return new ImageResponseDto(image.getId(), image.getName(), Objects.requireNonNull(image.getImageUrl()));
    }

    @Transactional
    public void addImageToSong(String imageId, String songId) {
        imageRepository.addImageToSong(imageId, songId);
    }

    @Transactional
    public ImageResponseDto removeImage(String imageId) {
        Image fromDb = imageRepository
                .findById(imageId)
                .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Image not found by given id"));
        imageRepository.delete(fromDb);
        return convertToResponseDto(fromDb);
    }

    public ImageResponseDto getImageBySongId(String songId) {
        return convertToResponseDto(Objects.requireNonNull(songService.findById(songId).getImage()));
    }

    @Transactional
    public ResponseEntity<Map> uploadImage(ImageModel imageModel) {

        try {
            if (imageModel.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Image image = new Image
                    .Builder()
                    .name(imageModel.getName())
                    .imageUrl(cloudinaryServiceImpl.uploadFile(imageModel.getFile(), "folder_1"))
                    .build();
            if (image.getImageUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url", image.getImageUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
