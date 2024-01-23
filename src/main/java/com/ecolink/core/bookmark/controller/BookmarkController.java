package com.ecolink.core.bookmark.controller;

import com.ecolink.core.bookmark.dto.response.BookmarkResponse;
import com.ecolink.core.bookmark.service.BookmarkService;
import com.ecolink.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ecolink.core.auth.token.UserPrincipal;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/stores")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@Tag(name = "${swagger.tag.bookmark}")
	@Operation(summary = "매장 북마크 등록 API - 인증 필요",
		description = "매장 북마크 등록 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/{Id}/bookmarks")
	public ApiResponse<BookmarkResponse> addBookmark(
		@PathVariable("Id") Long storeId,
		@RequestParam Long avatarId,
		@AuthenticationPrincipal UserPrincipal principal) {
		BookmarkResponse bookmarkResponse = bookmarkService.addBookmark(avatarId, storeId);
		return ApiResponse.ok(bookmarkResponse);
	}
}