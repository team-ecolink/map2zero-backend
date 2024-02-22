package com.ecolink.core.avatar.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.avatar.dto.request.MyPageBookmarkRequest;
import com.ecolink.core.avatar.dto.request.MyPageReviewRequest;
import com.ecolink.core.avatar.dto.response.GetMyPageResponse;
import com.ecolink.core.avatar.dto.response.MyPageBookmarkResponse;
import com.ecolink.core.avatar.service.MyPageService;
import com.ecolink.core.bookmark.service.BookmarkListService;
import com.ecolink.core.common.response.ApiCursorPageResponse;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.avatar.dto.response.MyPageReviewResponse;
import com.ecolink.core.review.service.ReviewSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/my-page")
public class MyPageController {

	private final MyPageService myPageService;
	private final ReviewSearchService reviewSearchService;
	private final BookmarkListService bookmarkListService;

	@Tag(name = "${swagger.tag.my-page}")
	@Operation(summary = "마이페이지 정보 조회 API - 인증 필요",
		description = "마이페이지 정보 조회 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ApiResponse<GetMyPageResponse> myPageInfo(
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(myPageService.getMyPageInfo(principal.getAvatarId()));
	}

	@Tag(name = "${swagger.tag.my-page}")
	@Operation(summary = "마이페이지 리뷰 조회 API - 인증 필요",
		description = "마이페이지 리뷰 조회 - 인증 필요, 커서 페이징",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/reviews")
	public ApiCursorPageResponse<MyPageReviewResponse, Long> myPageReviews(
		@ParameterObject @Valid MyPageReviewRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiCursorPageResponse.ok(reviewSearchService.getReviewsByWriter(request, principal.getAvatarId(),
			principal.getAvatarId()));
	}

	@Tag(name = "${swagger.tag.my-page}")
	@Operation(summary = "마이페이지 북마크 리스트 조회 API - 인증 필요",
		description = "마이페이지 북마크 리스트 조회 - 인증 필요, 커서 페이징",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/bookmarks")
	public ApiCursorPageResponse<MyPageBookmarkResponse, Long> myPageBookmarks(
		@ParameterObject @Valid MyPageBookmarkRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiCursorPageResponse.ok(bookmarkListService.getByStoreAndAvatar(request, principal.getAvatarId()));
	}

}
