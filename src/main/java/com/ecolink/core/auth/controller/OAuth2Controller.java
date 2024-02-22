package com.ecolink.core.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.dto.response.OAuth2StateResponse;
import com.ecolink.core.auth.service.OAuth2RequestService;
import com.ecolink.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/oauth2")
public class OAuth2Controller {

	private final OAuth2RequestService oauth2RequestService;

	@Tag(name = "${swagger.tag.sign-in}")
	@Operation(summary = "소셜 로그인 state 토큰 발급 API",
		description = "클라이언트를 확인하고 클라이언트 확인에 필요한 state 값을 반환해줍니다. "
			+ "또한 `Authorization` 헤더로 로그인 요청(`GET /oauth/code`)에 쓰일 임시세션토큰을 발급합니다.")
	@GetMapping("/state/{provider}")
	public ApiResponse<OAuth2StateResponse> session(HttpServletRequest request, HttpServletResponse response,
		@Parameter(description = "소셜 로그인 서비스의 이름", example = "kakao")
		@PathVariable("provider") String provider) {
		return ApiResponse.ok(oauth2RequestService.createRequest(request, response, provider));
	}

}
