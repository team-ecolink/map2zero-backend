package com.ecolink.core.bookmark.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecolink.core.bookmark.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	@Query("select (count(b) > 0) from Bookmark b "
		+ "where b.avatar.id = :avatarId "
		+ "and b.store.id = :storeId")
	boolean existsByAvatarAndStore(@Param("avatarId") Long avatarId, @Param("storeId") Long storeId);

	Optional<Bookmark> findBookmarkByAvatarIdAndStoreId(Long avatarId, Long storeId);

}