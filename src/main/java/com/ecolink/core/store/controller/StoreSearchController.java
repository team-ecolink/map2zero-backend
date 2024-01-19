package com.ecolink.core.store.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.ecolink.core.store.service.StoreSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Store search", description = "매장 검색 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreSearchController {

	private final StoreSearchService storeSearchService;

	@Tag(name = "Store search")
	@Operation(summary = "매장 검색 조회 API - 인증 선택",
		description = "검색어가 포함된 매장을 조회하는 API",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/search")
	public ResponseEntity<List<StoreSearchDto>> searchStores(
		@ParameterObject @Valid StoreSearchRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		return new ResponseEntity<>(storeSearchService.searchStores(request, 1L), HttpStatus.OK);
	}
}
