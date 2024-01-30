package com.ecolink.core.dev.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.dto.response.AuthenticationResponse;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.dev.service.DevService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@Profile({"local", "dev"})
public class DevController {

	private final DevService devService;

	@Tag(name = "개발용 API")
	@Operation(summary = "테스트용 세션 토큰 발급 API",
		description = """
			## **개발 서버 전용** (테스트용) 
			테스트 API 호출을 위해 임시로 카카오 유저 세션 토큰을 발급 받을 수 있는 API
			
			입력받은 email 로 로그인/회원가입을 진행합니다.
			
			`authorization` 헤더로 세션 토큰이 발급됩니다. 
			""")
	@GetMapping("/session")
	public ApiResponse<AuthenticationResponse> test(
		@RequestParam("email") @NotBlank @Email @Parameter(example = "test@kakao.com") String email,
		HttpServletRequest request) {
		return ApiResponse.ok(devService.createTempSession(email, request));
	}
}
