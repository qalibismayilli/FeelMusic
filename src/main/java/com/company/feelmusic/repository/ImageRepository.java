package com.company.feelmusic.repository;

import com.company.feelmusic.dto.response.ImageResponseDto;
import com.company.feelmusic.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> , JpaSpecificationExecutor<Image> {

    @Query("update Song s set s.image.id=:imageId where s.id=:songId")
    @Modifying
    @Transactional
    ImageResponseDto addImageToSong(String imageId, String songId );


    List<Image> getImagesBySongId(String songId);

}
