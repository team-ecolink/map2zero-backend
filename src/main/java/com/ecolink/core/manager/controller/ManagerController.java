package com.ecolink.core.manager.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.manager.dto.request.ManagerApplicationRequest;
import com.ecolink.core.manager.service.ManagerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/managers")
public class ManagerController {

	private final ManagerService managerService;

	@Tag(name = "${swagger.tag.my-page}")
	@Operation(summary = "점주 신청 API - 인증 필요",
		description = "점주 신청 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER') and !hasRole('MANAGER')")
	@PostMapping
	public ApiResponse<Void> applyManger(
		@RequestBody ManagerApplicationRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		managerService.applyManager(request, principal);
		return ApiResponse.ok();
	}

}
