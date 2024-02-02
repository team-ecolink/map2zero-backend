package com.ecolink.core.store.controller;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.common.response.ApiPageResponse;
import com.ecolink.core.store.dto.response.GetStoreProductResponse;
import com.ecolink.core.store.service.StoreProductSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class StoreProductController {

	private final StoreProductSearchService storeProductSearchService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "매장 상품 리스트 API", description = "매장 상품 리스트")
	@PageableAsQueryParam
	@GetMapping("/stores/{storeId}/products")
	public ApiPageResponse<GetStoreProductResponse> storeProductList(
		@PathVariable("storeId") Long storeId,
		@Parameter(hidden = true)
		@PageableDefault(size = 6) Pageable pageable) {
		return ApiPageResponse.ok(storeProductSearchService.getProductList(storeId, pageable));
	}
}
