package com.ecolink.core.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.dto.request.MyPageBookmarkRequest;
import com.ecolink.core.avatar.dto.response.MyPageBookmarkResponse;
import com.ecolink.core.common.dto.CursorPage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookmarkListService {

	private final BookmarkService bookmarkService;

	public CursorPage<MyPageBookmarkResponse, Long> getByStoreAndAvatar(MyPageBookmarkRequest request, Long avatarId) {

		List<MyPageBookmarkResponse> bookmarkResponses = bookmarkService.findBookmarkedStores(request, avatarId);

		return CursorPage.of(bookmarkResponses, request.getSize(), MyPageBookmarkResponse::getId);
	}

}