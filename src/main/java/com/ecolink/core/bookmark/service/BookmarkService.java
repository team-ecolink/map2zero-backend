package com.ecolink.core.bookmark.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.repository.AvatarRepository;
import com.ecolink.core.bookmark.domain.Bookmark;
import com.ecolink.core.bookmark.dto.response.BookmarkResponse;
import com.ecolink.core.bookmark.repository.BookmarkJpaRepository;
import com.ecolink.core.bookmark.dto.request.BookmarkRequest;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.repository.StoreJpaRepository;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;

@Transactional(readOnly = true)
@Service
public class BookmarkService {

	private final BookmarkJpaRepository bookmarkRepository;
	private final AvatarRepository avatarRepository;
	private final StoreJpaRepository storeRepository;

	public BookmarkService(BookmarkJpaRepository bookmarkRepository, AvatarRepository avatarRepository, StoreJpaRepository storeRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.avatarRepository = avatarRepository;
		this.storeRepository = storeRepository;
	}

	public BookmarkResponse addBookmark(BookmarkRequest request) {
		Avatar avatar = avatarRepository.findById(request.avatarId())
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_FOUND));

		Store store = storeRepository.findById(request.storeId())
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.STORE_NOT_FOUND)).getStore();

		Bookmark bookmark = new Bookmark(avatar, store);

		Bookmark savedBookmark = bookmarkRepository.save(bookmark);

		return BookmarkResponse.from(savedBookmark);
	}
}
