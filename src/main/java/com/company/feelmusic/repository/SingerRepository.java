package com.company.feelmusic.repository;

import com.company.feelmusic.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SingerRepository extends JpaRepository<String, Singer> , JpaSpecificationExecutor<Singer> {

}
