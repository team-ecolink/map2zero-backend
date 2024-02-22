package com.ecolink.core.product.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.config.swagger.annotation.SwaggerBody;
import com.ecolink.core.common.response.ApiCursorPageResponse;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.product.dto.request.GetStoreProductRequest;
import com.ecolink.core.product.dto.request.PatchSaleStatusRequest;
import com.ecolink.core.product.dto.request.PostStoreProductRequest;
import com.ecolink.core.product.dto.response.GetStoreProductResponse;
import com.ecolink.core.product.service.StoreProductCudService;
import com.ecolink.core.product.service.StoreProductSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("${api.prefix}")
public class StoreProductController {

	private final StoreProductSearchService storeProductSearchService;
	private final StoreProductCudService storeProductCudService;

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

	@Tag(name = "${swagger.tag.manager}")
	@Operation(summary = "점주 매장 제품 등록 API- 인증 필요",
		description = "점주 매장 제품 등록 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@SwaggerBody(content = @Content(
		encoding = @Encoding(name = "request", contentType = MediaType.APPLICATION_JSON_VALUE)))
	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping(value = "/m/stores/{id}/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<Long> registerStoreProduct(
		@Parameter(description = "추가할 제품의 사진 최소, 최대 1개")
		@RequestPart(name = "images") @Size(min = 1, max = 1) @NotNull List<MultipartFile> images,
		@Parameter(description = "매장 ID", example = "2")
		@PathVariable("id") @NotNull @Positive Long storeId,
		@Parameter(description = "추가할 제품의 정보")
		@RequestPart("request") @Valid PostStoreProductRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(storeProductCudService.registerStoreProduct(storeId, request, images, principal));
	}

	@Tag(name = "${swagger.tag.manager}")
	@Operation(summary = "매장제품 판매 상태 변경 API- 인증 필요",
		description = "매장제품 판매 상태 변경 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('MANAGER')")
	@PatchMapping(value = "/m/products/sale")
	public ApiResponse<Void> changeSaleStatus(
		@RequestBody @Valid PatchSaleStatusRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		storeProductCudService.changeSaleStatus(request, principal);
		return ApiResponse.ok();
	}

}
