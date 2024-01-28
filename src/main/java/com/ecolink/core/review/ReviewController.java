package com.ecolink.core.review;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.common.util.AuthorityUtil;
import com.ecolink.core.review.dto.response.ReviewResponse;
import com.ecolink.core.review.dto.request.ReviewSearchRequest;
import com.ecolink.core.review.service.ReviewSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class ReviewController {

	private final ReviewSearchService reviewSearchService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "리뷰 리스트 조회 API - 인증 선택", description = "리뷰 리스트 조회 - 인증 선택",
		security = {@SecurityRequirement(name = "session-token")})
	@GetMapping("/stores/{storeId}/reviews")
	public ApiResponse<List<ReviewResponse>> reviewList(
		@PathVariable("storeId") Long storeId,
		@ParameterObject @Valid ReviewSearchRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		if(AuthorityUtil.hasUserAuthority(principal)) {
			return ApiResponse.ok(reviewSearchService.getByStore(storeId, principal.getAvatarId(), request));
		}
		return ApiResponse.ok(reviewSearchService.getByStore(storeId, null, request));
	}
}
