package com.ecolink.core.avatar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.dto.request.MyPageBookmarkRequest;
import com.ecolink.core.avatar.dto.response.GetMyPageResponse;
import com.ecolink.core.avatar.dto.response.MyPageBookmarkResponse;
import com.ecolink.core.bookmark.domain.Bookmark;
import com.ecolink.core.bookmark.dto.response.GetBookmarkResponse;
import com.ecolink.core.bookmark.service.BookmarkPaginationService;
import com.ecolink.core.bookmark.service.BookmarkService;
import com.ecolink.core.common.dto.CursorPage;
import com.ecolink.core.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageService {

	private final AvatarService avatarService;
	//private final BookmarkService bookmarkService;

	public GetMyPageResponse getMyPageInfo(Long avatarId) {
		Avatar avatar = avatarService.getUserGraphById(avatarId);
		User user = avatar.getUser();
		return GetMyPageResponse.of(user, avatar, user.isManager());
	}

}