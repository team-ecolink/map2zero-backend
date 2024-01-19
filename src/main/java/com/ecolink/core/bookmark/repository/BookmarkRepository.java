package com.ecolink.core.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.bookmark.domain.Bookmark;
import com.ecolink.core.store.domain.Store;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	boolean existsBookmarkByAvatarAndStore(Avatar avatar, Store store);
}
