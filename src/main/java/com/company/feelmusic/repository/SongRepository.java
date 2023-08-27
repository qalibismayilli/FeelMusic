package com.company.feelmusic.repository;

import com.company.feelmusic.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface SongRepository extends JpaRepository<Song, String>, JpaSpecificationExecutor<Song> {

}
