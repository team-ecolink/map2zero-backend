package com.ecolink.core.bookmark.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.dto.request.MyPageBookmarkRequest;
import com.ecolink.core.avatar.dto.response.MyPageBookmarkResponse;
import com.ecolink.core.common.dto.CursorPage;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookmarkPaginationService {

	private final BookmarkService bookmarkService;
	private final StoreService storeService;

	public CursorPage<MyPageBookmarkResponse, Long> getByStoreAndAvatar(MyPageBookmarkRequest request, Pageable pageable, Long avatarId) {
		int pageSize = pageable.getPageSize();
		Long lastBookmarkId = null;

		List<MyPageBookmarkResponse> bookmarkResponses = bookmarkService.findBookmarkedStores(request, lastBookmarkId, pageSize, avatarId);

		return CursorPage.of(bookmarkResponses, pageSize, MyPageBookmarkResponse::getId);
	}
}