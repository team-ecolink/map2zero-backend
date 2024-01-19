package com.ecolink.core.avatar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.avatar.dto.response.GetMyPageResponse;
import com.ecolink.core.avatar.service.MyPageService;
import com.ecolink.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/my-page")
public class MyPageController {

	private final MyPageService myPageService;

	@Tag(name = "${swagger.tag.my-page}")
	@Operation(summary = "마이페이지 정보 조회 API - 인증 필요",
		description = "마이페이지 정보 조회 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ApiResponse<GetMyPageResponse> myPageInfo(
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(myPageService.getMyPageInfo(principal.getAvatarId()));
	}

}
