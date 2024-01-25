package com.ecolink.core.bookmark.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.bookmark.repository.BookmarkRepository;
import com.ecolink.core.store.domain.Store;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;

	public Boolean isBookmarked(Avatar avatar, Store store) {
		if (avatar == null) {
			return false;
		} else {
			return bookmarkRepository.existsBookmarkByAvatarIdAndStoreId(avatar.getId(), store.getId());
		}
	}
}
