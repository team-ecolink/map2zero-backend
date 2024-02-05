package com.ecolink.core.review.controller;

import java.util.List;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.config.swagger.annotation.SwaggerBody;
import com.ecolink.core.common.response.ApiPageResponse;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.common.util.AuthorityUtil;
import com.ecolink.core.review.dto.request.CreateReviewRequest;
import com.ecolink.core.review.dto.response.GetReviewResponse;
import com.ecolink.core.review.service.ReviewCreateService;
import com.ecolink.core.review.service.ReviewSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class ReviewController {

	private final ReviewSearchService reviewSearchService;
	private final ReviewCreateService reviewCreateService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "리뷰 리스트 조회 API - 인증 선택",
		description = "리뷰 리스트 조회 - 인증 선택",
		security = {@SecurityRequirement(name = "session-token")})
	@PageableAsQueryParam
	@GetMapping("/stores/{storeId}/reviews")
	public ApiPageResponse<GetReviewResponse> reviewList(
		@PathVariable("storeId") Long storeId,
		@AuthenticationPrincipal UserPrincipal principal,
		@Parameter(hidden = true)
		@PageableDefault(size = 4, sort = "likeCnt", direction = Sort.Direction.DESC) Pageable pageable) {
		if (AuthorityUtil.hasUserAuthority(principal)) {
			return ApiPageResponse.ok(reviewSearchService.getByStore(storeId, pageable, principal.getAvatarId()));
		}
		return ApiPageResponse.ok(reviewSearchService.getByStore(storeId, pageable, null));
	}

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "내가 쓴 리뷰 리스트 조회 API - 인증 필수",
		description = "내가 쓴 리뷰 리스트 조회 - 인증 필수",
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

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "리뷰 쓰기 API - 인증 필요",
		description = """
			리뷰 쓰기 - 인증 필요

			request 부분의 `content-type`을 반드시 `application/json`으로 지정해서 요청해야 합니다.
			""",
		security = {@SecurityRequirement(name = "session-token")})
	@SwaggerBody(content = @Content(
		encoding = @Encoding(name = "request", contentType = MediaType.APPLICATION_JSON_VALUE)))
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/reviews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<Void> createReview(
		@Parameter(description = "추가할 리뷰 사진")
		@RequestPart(name = "images", required = false) @Nullable List<MultipartFile> files,
		@Parameter(description = "추가할 리뷰 정보")
		@RequestPart("request") @Valid CreateReviewRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		reviewCreateService.createReview(request, files, principal.getAvatarId());
		return ApiResponse.ok();
	}

}
