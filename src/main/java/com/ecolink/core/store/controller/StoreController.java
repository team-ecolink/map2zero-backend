package com.ecolink.core.store.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.store.dto.response.StoreDetailResponse;
import com.ecolink.core.store.dto.response.StoreInfoResponse;
import com.ecolink.core.store.service.StoreQueryService;
import com.ecolink.core.user.constant.RoleType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/stores")
public class StoreController {

	private final StoreQueryService storeQueryService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "매장 상세 페이지 API", description = "매장 상세 페이지 - 인증 선택",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/{id}")
	public ApiResponse<StoreDetailResponse> storeInfo(@PathVariable("id") Long id) {
		// if(userPrincipal.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.USER.getAuthority()))){
		// 	return ApiResponse.ok(storeQueryService.getStoreDetailPage(id, userPrincipal.getAvatarId()));
		// }
		return ApiResponse.ok(storeQueryService.getStoreDetailPage(id, null));
	}

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "매장 정보 조회 API", description = "매장 정보 조회")
	@GetMapping("/{id}/info")
	public ApiResponse<StoreInfoResponse> storeDetailInfo(@PathVariable("id") Long id) {
		return ApiResponse.ok(storeQueryService.getStoreInfo(id));
	}
}
