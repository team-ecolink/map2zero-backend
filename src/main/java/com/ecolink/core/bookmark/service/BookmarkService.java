package com.ecolink.core.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.dto.request.MyPageBookmarkRequest;
import com.ecolink.core.avatar.dto.response.MyPageBookmarkResponse;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.bookmark.domain.Bookmark;
import com.ecolink.core.bookmark.repository.BookmarkJpaRepository;
import com.ecolink.core.bookmark.repository.BookmarkRepository;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.BookmarkAlreadyExistsException;
import com.ecolink.core.common.error.exception.BookmarkNotFoundException;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;
	private final BookmarkJpaRepository bookmarkJpaRepository;
	private final AvatarService avatarService;
	private final StoreService storeService;

	public boolean existsBookmark(Long avatarId, Long storeId) {
		return bookmarkRepository.existsByAvatarAndStore(avatarId, storeId);
	}

	@Transactional
	public Bookmark addBookmark(Long storeId, Long avatarId) {
		Avatar avatar = avatarService.getById(avatarId);
		Store store = storeService.getById(storeId);

		if (existsBookmark(avatarId, storeId)) {
			throw new BookmarkAlreadyExistsException(ErrorCode.BOOKMARK_ALREADY_EXISTS);
		}

		Bookmark bookmark = new Bookmark(store, avatar);
		Bookmark savedBookmark = bookmarkRepository.save(bookmark);

		store.addBookmarkCount();

		return savedBookmark;
	}

	public Bookmark getBookmark(Long storeId, Long avatarId) {
		return bookmarkRepository.findBookmarkByAvatarIdAndStoreId(avatarId, storeId)
			.orElseThrow(() -> new BookmarkNotFoundException(ErrorCode.BOOKMARK_NOT_FOUND));
	}

	@Transactional
	public void deleteBookmark(Long storeId, Long avatarId) {
		Bookmark bookmark = getBookmark(storeId, avatarId);
		Store store = bookmark.getStore();

		bookmarkRepository.delete(bookmark);

		store.deleteBookmarkCount();
	}

	public List<MyPageBookmarkResponse> findBookmarkedStores(MyPageBookmarkRequest request, Long avatarId) {
		return bookmarkJpaRepository.findBookmarkedStores(request, avatarId);
	}

}