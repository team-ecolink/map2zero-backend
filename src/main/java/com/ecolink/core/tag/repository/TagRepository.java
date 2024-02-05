package com.ecolink.core.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.tag.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
