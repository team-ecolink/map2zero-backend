package com.ecolink.core.store.controller;

import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.store.dto.response.GetStoreProductResponse;
import com.ecolink.core.store.service.StoreProductSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class StoreProductController {

    private final StoreProductSearchService storeProductSearchService;

    @Tag(name = "${swagger.tag.store}")
    @Operation(summary = "매장 상품 리스트 API", description = "매장 상품 리스트")
    @GetMapping("/stores/{storeId}/products")
    public ApiResponse<List<GetStoreProductResponse>> storeProductList(@PathVariable("storeId") Long storeId) {
        return ApiResponse.ok(storeProductSearchService.getProductList(storeId));
    }
}
