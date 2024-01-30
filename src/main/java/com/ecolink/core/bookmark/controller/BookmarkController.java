package com.ecolink.core.bookmark.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.bookmark.dto.request.BookmarkRequest;
import com.ecolink.core.bookmark.dto.response.BookmarkResponse;
import com.ecolink.core.bookmark.service.BookmarkService;
import com.ecolink.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/bookmarks")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@Tag(name = "${swagger.tag.bookmark}")
	@Operation(summary = "매장 북마크 등록 API - 인증 필요",
		description = "매장 북마크 등록 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public ApiResponse<Void> addBookmark(
		@RequestBody BookmarkRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		bookmarkService.addBookmark(principal.getAvatarId(), request.storeId());
		return ApiResponse.ok();
	}

	@Tag(name = "${swagger.tag.bookmark}")
	@Operation(summary = "매장 북마크 취소 API - 인증 필요",
		description = "매장 북마크 취소 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping
	public ApiResponse<Void> deleteBookmark(
		@RequestBody BookmarkRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		bookmarkService.deleteBookmark(principal.getAvatarId(), request.storeId());
		return ApiResponse.ok();
	}
}