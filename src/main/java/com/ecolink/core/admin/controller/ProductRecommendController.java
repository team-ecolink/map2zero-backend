package com.ecolink.core.admin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.admin.dto.ProductInfoDto;
import com.ecolink.core.admin.service.ProductRecommendService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductRecommendController {

	private final ProductRecommendService productRecommendService;

	@Tag(name = "${swagger.tag.home}")
	@Operation(summary = "오늘의 제로웨이스트 상품 조회 API",
		description = "오늘의 제로웨이스트 상품 조회")
	@GetMapping("/today")
	public ApiResponse<List<ProductInfoDto>> getTodayProducts() {
		return ApiResponse.ok(productRecommendService.getTodayProducts());
	}

	@Tag(name = "${swagger.tag.home}")
	@Operation(summary = "나만의 제품을 만나보세요 조회 API",
		description = "나만의 제품을 만나보세요 조회")
	@GetMapping("/my")
	public ApiResponse<List<ProductInfoDto>> getMyProducts() {
		return ApiResponse.ok(productRecommendService.getMyProducts());
	}
}
