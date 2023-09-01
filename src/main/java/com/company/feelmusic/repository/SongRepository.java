package com.company.feelmusic.repository;

import com.company.feelmusic.model.Category;
import com.company.feelmusic.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, String>, JpaSpecificationExecutor<Song> {
    List<Song> findAllByName(String name);

    List<Song> findAllBySinger(String singer);

    List<Song> findAllByCategory(Category category);
}
