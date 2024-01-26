package com.ecolink.core.avatar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.avatar.service.NicknameService;
import com.ecolink.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/avatars")
public class AvatarController {

	private final NicknameService nicknameService;

	@Tag(name = "${swagger.tag.sign-up}")
	@Tag(name = "${swagger.tag.account}")
	@Operation(summary = "중복 닉네임 확인 API",
		description = "중복 닉네임 확인 API")
	@GetMapping("/nickname/inuse")
	public ApiResponse<Boolean> checkIfNicknameUnique(
		@Parameter(description = "중복을 체크할 닉네임") @RequestParam("nickname") String nickname) {
		return ApiResponse.ok(nicknameService.isNicknameInUse(nickname));
	}

}
