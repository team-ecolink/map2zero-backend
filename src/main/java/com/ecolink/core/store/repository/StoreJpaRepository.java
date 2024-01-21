package com.ecolink.core.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.bookmark.domain.Bookmark;

public interface StoreJpaRepository extends JpaRepository<Bookmark, Long> {
}
