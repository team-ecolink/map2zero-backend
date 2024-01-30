package com.ecolink.core.bookmark.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.bookmark.domain.Bookmark;
import com.ecolink.core.bookmark.dto.response.BookmarkResponse;
import com.ecolink.core.bookmark.repository.BookmarkRepository;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.BookmarkAlreadyExistsException;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;
	private final AvatarService avatarService;
	private final StoreService storeService;

	public boolean existsBookmark(Long avatarId, Long storeId) {
		return bookmarkRepository.existsByAvatarAndStore(avatarId, storeId);
	}

	@Transactional
	public BookmarkResponse addBookmark(Long avatarId, Long storeId) {
		Avatar avatar = avatarService.getById(avatarId);
		Store store = storeService.getById(storeId);

		if (existsBookmark(avatarId, storeId)) {
			throw new BookmarkAlreadyExistsException(ErrorCode.BOOKMARK_ALREADY_EXISTS);
		}

		Bookmark bookmark = new Bookmark(avatar, store);
		Bookmark savedBookmark = bookmarkRepository.save(bookmark);

		store.addBookmarkCount();

		return BookmarkResponse.of(savedBookmark);
	}
}
