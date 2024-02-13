package com.ecolink.core.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.user.service.WithdrawService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {

	private final WithdrawService withdrawService;

	@Tag(name = "${swagger.tag.account}")
	@Operation(summary = "회원 탈퇴 API - 인증 필요",
		description = "회원 탈퇴 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/withdraw")
	public ApiResponse<Void> withdraw(
		@AuthenticationPrincipal UserPrincipal principal) {
		withdrawService.withdraw(principal.getUserId());
		return ApiResponse.ok();
	}
}
