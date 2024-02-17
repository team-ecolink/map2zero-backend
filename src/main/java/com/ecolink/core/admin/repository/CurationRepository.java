package com.ecolink.core.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.admin.domain.Curation;

public interface CurationRepository extends JpaRepository<Curation, Long> {
}
