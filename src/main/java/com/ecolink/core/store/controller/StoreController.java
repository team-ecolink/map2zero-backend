package com.ecolink.core.store.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.common.util.AuthorityUtil;
import com.ecolink.core.store.dto.response.StoreDetailResponse;
import com.ecolink.core.store.dto.response.StoreTrendResponse;
import com.ecolink.core.store.service.StoreCudService;
import com.ecolink.core.store.service.StoreSearchService;
import com.ecolink.core.store.service.StoreTrendService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/stores")
public class StoreController {

	private final StoreSearchService storeSearchService;
	private final StoreTrendService storeTrendService;
	private final StoreCudService storeCudService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "매장 상세 페이지 API - 인증 선택", description = "매장 상세 페이지 - 인증 선택",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/{id}")
	public ApiResponse<StoreDetailResponse> storeInfo(
		@PathVariable("id") @NotNull @Positive Long id,
		@AuthenticationPrincipal UserPrincipal principal) {
		if (AuthorityUtil.hasUserAuthority(principal)) {
			return ApiResponse.ok(storeSearchService.getStoreDetailPage(id, principal.getAvatarId()));
		}
		return ApiResponse.ok(storeSearchService.getStoreDetailPage(id, null));
	}

	@Tag(name = "${swagger.tag.home}")
	@Operation(summary = "요즘 뜨는 매장 카드 조회 API",
		description = "요즘 뜨는 매장 카드 (북마크 순, 상위 10개)")
	@GetMapping("/trend")
	public ApiResponse<List<StoreTrendResponse>> getTrendyStores() {
		return ApiResponse.ok(storeTrendService.getTrendyStores());
	}

	@Tag(name = "${swagger.tag.admin}")
	@Operation(summary = "매장 사진 변경 API- 인증 필요",
		description = "매장 사진 변경 API - 인증 필요 (어드민 권한의 유저만 사용 가능)",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/m/stores/{id}/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<Void> changeStorePhotos(
		@Parameter(description = "변경할 매장의 사진 최소 1개 최대 10개")
		@RequestPart(name = "images") @Size(min = 1, max = 10) @NotNull List<MultipartFile> images,
		@Parameter(description = "매장 ID", example = "22")
		@PathVariable("id") @NotNull @Positive Long storeId,
		@AuthenticationPrincipal UserPrincipal principal) {
		storeCudService.changeStorePhotos(storeId, images, principal);
		return ApiResponse.ok();
	}

}
