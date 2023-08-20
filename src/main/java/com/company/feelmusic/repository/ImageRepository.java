package com.company.feelmusic.repository;

import com.company.feelmusic.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageRepository extends JpaRepository<String, Image> , JpaSpecificationExecutor<Image> {

}
