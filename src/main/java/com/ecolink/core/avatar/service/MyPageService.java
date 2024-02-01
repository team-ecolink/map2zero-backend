package com.ecolink.core.avatar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.dto.response.GetMyPageResponse;
import com.ecolink.core.bookmark.domain.Bookmark;
import com.ecolink.core.bookmark.dto.response.BookmarkedStoreInfoDto;
import com.ecolink.core.bookmark.service.BookmarkService;
import com.ecolink.core.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageService {

	private final AvatarService avatarService;
	private final BookmarkService bookmarkService;

	public GetMyPageResponse getMyPageInfo(Long avatarId) {
		Avatar avatar = avatarService.getUserGraphById(avatarId);
		User user = avatar.getUser();
		return GetMyPageResponse.of(user, avatar, user.isManager());
	}

	public List<BookmarkedStoreInfoDto> getBookmarkedList(Long avatarId) {
		List<Bookmark> bookmarks = bookmarkService.getBookmarksByAvatarId(avatarId);
		return bookmarks.stream()
			.map(bookmark -> BookmarkedStoreInfoDto.of(bookmark.getStore()))
			.collect(Collectors.toList());
	}

}
