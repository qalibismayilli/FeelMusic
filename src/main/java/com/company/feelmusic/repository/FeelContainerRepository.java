package com.company.feelmusic.repository;

import com.company.feelmusic.model.FeelContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FeelContainerRepository extends JpaRepository<FeelContainer, String> , JpaSpecificationExecutor<FeelContainer> {
}
