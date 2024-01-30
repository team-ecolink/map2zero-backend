package com.ecolink.core.avatar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.avatar.dto.request.UpdateNicknameRequest;
import com.ecolink.core.avatar.service.AvatarInfoService;
import com.ecolink.core.avatar.service.NicknameService;
import com.ecolink.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("${api.prefix}/avatars")
public class AvatarController {

	private final NicknameService nicknameService;
	private final AvatarInfoService avatarInfoService;

	@Tag(name = "${swagger.tag.sign-up}")
	@Tag(name = "${swagger.tag.account}")
	@Operation(summary = "중복 닉네임 확인 API",
		description = "중복 닉네임 확인, 중복이면 true를 반환합니다.")
	@GetMapping("/nickname/inuse")
	public ApiResponse<Boolean> checkIfNicknameUnique(
		@Parameter(description = "중복을 체크할 닉네임") @Size(min = 2, max = 8)
		@RequestParam("nickname") String nickname) {
		return ApiResponse.ok(nicknameService.isNicknameInUse(nickname));
	}

	@Tag(name = "${swagger.tag.sign-up}")
	@Tag(name = "${swagger.tag.account}")
	@Operation(summary = "닉네임 변경 API - 인증 필요",
		description = "닉네임 변경 - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("hasRole('USER')")
	@PatchMapping("/nickname")
	public ApiResponse<Void> updateNickname(
		@Valid @RequestBody UpdateNicknameRequest request,
		@AuthenticationPrincipal UserPrincipal principal) {
		avatarInfoService.updateNickname(request, principal.getAvatarId());
		return ApiResponse.ok();
	}

}
