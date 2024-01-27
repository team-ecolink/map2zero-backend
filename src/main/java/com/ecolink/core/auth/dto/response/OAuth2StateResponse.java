package com.ecolink.core.auth.dto.response;

import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import io.swagger.v3.oas.annotations.media.Schema;

public record OAuth2StateResponse(
	@Schema(description = "카카오 로그인페이지에 요청할 state 값",
		example = "6L7PMoiu8bPomn3_O2-_vMfHBFL4R9J-QCBduMcUQgc%3D")
	String state,
	@Schema(description = "로그인을 요청할 소셜 로그인 URL",
		example = "https://kauth.kakao.com/oauth/authorize?response_type=code"
			+ "&client_id=c2546718ccf31c87d450c89fad6f4c2b&scope=profile_nickname%20profile_image%20account_email"
			+ "&state=6L7PMoiu8bPomn3_O2-_vMfHBFL4R9J-QCBduMcUQgc%3D"
			+ "&redirect_uri=http://localhost:3000/login/auth/callback/kakao")
	String url
) {

	public static OAuth2StateResponse of(OAuth2AuthorizationRequest request) {
		return new OAuth2StateResponse(request.getState(), request.getAuthorizationRequestUri());
	}


}
