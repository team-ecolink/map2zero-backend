package com.ecolink.core.review.controller;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.util.AuthorityUtil;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.common.response.ApiPageResponse;
import com.ecolink.core.review.dto.response.GetReviewResponse;
import com.ecolink.core.review.service.ReviewSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class ReviewController {

	private final ReviewSearchService reviewSearchService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "리뷰 리스트 조회 API - 인증 선택", description = "리뷰 리스트 조회 - 인증 선택",
		security = {@SecurityRequirement(name = "session-token")})
	@PageableAsQueryParam
	@GetMapping("/stores/{storeId}/reviews")
	public ApiPageResponse<GetReviewResponse> reviewList(
		@PathVariable("storeId") Long storeId,
		 @AuthenticationPrincipal UserPrincipal principal,
		@Parameter(hidden = true)
		@PageableDefault(size = 4, sort = "likeCnt", direction = Sort.Direction.DESC) Pageable pageable) {
		 if(AuthorityUtil.hasUserAuthority(principal)) {
			return ApiPageResponse.ok(reviewSearchService.getByStore(storeId, pageable, principal.getAvatarId()));
		 }
		 return ApiPageResponse.ok(reviewSearchService.getByStore(storeId, pageable, null));
	}

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "내가 쓴 리뷰 리스트 조회 API - 인증 필수", description = "내가 쓴 리뷰 리스트 조회 - 인증 필수",
			security = {@SecurityRequirement(name = "session-token")})
	@PageableAsQueryParam
	@GetMapping("/stores/{storeId}/my-reviews")
	@PreAuthorize("hasRole('USER')")
	public ApiPageResponse<GetReviewResponse> myReviewList(
			@PathVariable("storeId") Long storeId,
			@AuthenticationPrincipal UserPrincipal principal,
			@Parameter(hidden = true)
			@PageableDefault(size = 4, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
		return ApiPageResponse.ok(reviewSearchService.getByStoreAndAvatar(storeId, pageable, principal.getAvatarId()));
	}
}
