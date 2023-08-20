package com.company.feelmusic.repository;

import com.company.feelmusic.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SongRepository extends JpaRepository<String, Song>, JpaSpecificationExecutor<Song> {

}
