package com.ecolink.core.store.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.common.util.AuthorityUtil;
import com.ecolink.core.store.dto.response.StoreDetailResponse;
import com.ecolink.core.store.service.StoreSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/stores")
public class StoreController {

	private final StoreSearchService storeSearchService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "매장 상세 페이지 API - 인증 선택", description = "매장 상세 페이지 - 인증 선택",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/{id}")
	public ApiResponse<StoreDetailResponse> storeInfo(
		@PathVariable("id") Long id,
		@AuthenticationPrincipal UserPrincipal principal) {
		if(AuthorityUtil.hasUserAuthority(principal)){
			return ApiResponse.ok(storeSearchService.getStoreDetailPage(id, principal.getAvatarId()));
		}
		return ApiResponse.ok(storeSearchService.getStoreDetailPage(id, null));
	}
}
