package com.ecolink.core.map.repository;

import com.ecolink.core.map.domain.MapContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDetailRepository extends JpaRepository<MapContent, Long> {
}
