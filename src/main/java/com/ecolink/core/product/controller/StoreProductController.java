package com.ecolink.core.product.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiCursorPageResponse;
import com.ecolink.core.product.dto.request.GetStoreProductRequest;
import com.ecolink.core.product.dto.response.GetStoreProductResponse;
import com.ecolink.core.product.service.StoreProductSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("${api.prefix}")
public class StoreProductController {

	private final StoreProductSearchService storeProductSearchService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "매장 판매 제품 목록 조회 API",
		description = "매장 판매 제품 목록 조회 API")
	@GetMapping("/stores/{id}/products")
	public ApiCursorPageResponse<GetStoreProductResponse, Long> searchStoreProducts(
		@Valid @ParameterObject GetStoreProductRequest request,
		@PathVariable("id") @NotNull @Positive Long storeId) {
		return ApiCursorPageResponse.ok(storeProductSearchService.searchStoreProducts(storeId, request));
	}

	@Tag(name = "${swagger.tag.manager}")
	@Operation(summary = "점주 매장 판매 제품 목록 조회 API- 인증 필요",
		description = "점주 매장 판매 제품 목록 조회 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/m/stores/{id}/products")
	public ApiCursorPageResponse<GetStoreProductResponse, Long> searchStoreProductsByManager(
		@Valid @ParameterObject GetStoreProductRequest request,
		@PathVariable("id") @NotNull @Positive Long storeId,
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiCursorPageResponse.ok(
			storeProductSearchService.searchStoreProductsByManager(storeId, request, principal));
	}

}
