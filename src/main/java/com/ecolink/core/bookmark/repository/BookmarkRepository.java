package com.ecolink.core.bookmark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.bookmark.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	boolean existsBookmarkByAvatarIdAndStoreId(Long avatarId, Long storeId);
	Optional<Bookmark> findBookmarkByAvatarIdAndStoreId(Long avatarId, Long storeId);
}
