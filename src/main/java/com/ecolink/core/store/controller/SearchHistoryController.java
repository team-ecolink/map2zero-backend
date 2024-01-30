package com.ecolink.core.store.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.store.dto.response.SearchHistoryDto;
import com.ecolink.core.store.service.SearchHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/keywords")
public class SearchHistoryController {

	private final SearchHistoryService searchHistoryService;

	@Tag(name = "${swagger.tag.search}")
	@Operation(summary = "최근 검색어 조회 API - 인증 필요",
		description = "최근 검색어 조회 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/recent")
	public ApiResponse<List<SearchHistoryDto>> getRecentKeywords(
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(searchHistoryService.getSearchHistoryList(principal.getAvatarId()));
	}
}
