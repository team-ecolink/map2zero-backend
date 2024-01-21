package com.ecolink.core.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecolink.core.bookmark.domain.Bookmark;

public interface BookmarkJpaRepository extends JpaRepository<Bookmark, Long> {
}
