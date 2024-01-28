package com.ecolink.core.store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.store.dto.response.SearchHistoryDto;
import com.ecolink.core.store.service.SearchHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/keywords")
public class SearchHistoryController {

	private final SearchHistoryService searchHistoryService;

	@Tag(name = "${swagger.tag.search}")
	@Operation(summary = "최근 검색어 조회 API",
		description = "최근 검색어 조회 API")
	@GetMapping("/recent")
	public ApiResponse<List<SearchHistoryDto>> getRecentKeywords(
		@Parameter(description = "특정 아바타에 대한 최근 검색어를 조회하기 위한 아바타 ID") @RequestParam Long avatarId) {
		return ApiResponse.ok(searchHistoryService.getSearchHistoryList(avatarId));
	}
}
