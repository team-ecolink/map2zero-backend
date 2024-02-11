package com.ecolink.core.store.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.bookmark.dto.response.StoreInfoDto;
import com.ecolink.core.common.response.ApiCursorPageResponse;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.common.util.AuthorityUtil;
import com.ecolink.core.store.dto.MapStoreInfoDto;
import com.ecolink.core.store.dto.request.MapQueryRequest;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.ecolink.core.store.service.StoreSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/stores")
public class StoreSearchController {

	private final StoreSearchService storeSearchService;

	@Tag(name = "${swagger.tag.search}")
	@Operation(summary = "매장 검색 조회 API - 인증 선택",
		description = "검색어가 포함된 매장을 조회하는 API - 인증 선택",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/search")
	public ApiCursorPageResponse<StoreSearchDto, Long> searchStores(
		@ParameterObject @Valid StoreSearchRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {

		if (AuthorityUtil.hasUserAuthority(principal)) {
			return ApiCursorPageResponse.ok(storeSearchService.searchStores(request, principal.getAvatarId()));
		}
		return ApiCursorPageResponse.ok(storeSearchService.searchStores(request, null));
	}

	@Tag(name = "${swagger.tag.search}")
	@Operation(summary = "인기 매장 조회 API",
		description = "인기 매장 조회 API")
	@GetMapping("/popular")
	public ApiResponse<List<StoreInfoDto>> getTop10PopularStores() {
		return ApiResponse.ok(storeSearchService.getTop10PopularStores());
	}

	@Tag(name = "${swagger.tag.map}")
	@Operation(summary = "맵 매장 검색 API - 인증 선택",
		description = "맵 매장 검색 API - 인증이 되면 북마크 여부가 포함됩니다, 현재 위치로부터의 거리순으로 정렬되어 있습니다.",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/map")
	public ApiResponse<List<MapStoreInfoDto>> storeInfoList(
		@Valid @ParameterObject MapQueryRequest mapQueryRequest,
		@AuthenticationPrincipal UserPrincipal principal) {
		if (AuthorityUtil.hasUserAuthority(principal)) {
			return ApiResponse.ok(storeSearchService.getMapStoreInfoList(mapQueryRequest, principal.getAvatarId()));
		}
		return ApiResponse.ok(storeSearchService.getMapStoreInfoList(mapQueryRequest, null));
	}

	@Tag(name = "${swagger.tag.map}")
	@Operation(summary = "가장 가까운 매장 조회 API - 인증 선택",
		description = "가장 가까운 매장 조회 API - 인증이 되면 북마크 여부가 포함됩니다",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/map/nearest")
	public ApiResponse<MapStoreInfoDto> getNearestStore(
		@Valid @ParameterObject MapQueryRequest mapQueryRequest,
		@AuthenticationPrincipal UserPrincipal principal) {
		if (AuthorityUtil.hasUserAuthority(principal)) {
			return ApiResponse.ok(storeSearchService.getNearestStore(mapQueryRequest, principal.getAvatarId()));
		}
		return ApiResponse.ok(storeSearchService.getNearestStore(mapQueryRequest, null));
	}

}
