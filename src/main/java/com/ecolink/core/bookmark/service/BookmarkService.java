package com.ecolink.core.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.bookmark.domain.Bookmark;
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
	private final AvatarService avatarService;
	private final StoreService storeService;

	public boolean existsBookmark(Long storeId, Long avatarId) {
		return bookmarkRepository.existsByAvatarAndStore(storeId, avatarId);
	}

	@Transactional
	public void addBookmark(Long storeId, Long avatarId) {
		Store store = storeService.getById(storeId);
		Avatar avatar = avatarService.getById(avatarId);

		if (existsBookmark(storeId, avatarId)) {
			throw new BookmarkAlreadyExistsException(ErrorCode.BOOKMARK_ALREADY_EXISTS);
		}

		Bookmark bookmark = new Bookmark(store, avatar);
		bookmarkRepository.save(bookmark);

		store.addBookmarkCount();
	}

	public Bookmark getBookmark(Long storeId, Long avatarId) {
		return bookmarkRepository.findBookmarkByAvatarIdAndStoreId(storeId, avatarId)
			.orElseThrow(() -> new BookmarkNotFoundException(ErrorCode.BOOKMARK_NOT_FOUND));
	}

	@Transactional
	public void deleteBookmark(Long storeId, Long avatarId) {
		Bookmark bookmark = getBookmark(storeId, avatarId);
		Store store = bookmark.getStore();

		bookmarkRepository.delete(bookmark);

		store.deleteBookmarkCount();
	}

	public List<Bookmark> getBookmarksByAvatarId(Long avatarId) {
		return bookmarkRepository.findAllByAvatarId(avatarId);
	}

}