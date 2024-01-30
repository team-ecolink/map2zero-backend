package com.ecolink.core.dev.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ecolink.core.auth.dto.AuthenticationResult;
import com.ecolink.core.auth.dto.response.AuthenticationResponse;
import com.ecolink.core.auth.model.OAuth2Attributes;
import com.ecolink.core.auth.service.AuthenticationService;
import com.ecolink.core.auth.service.SecurityContextService;
import com.ecolink.core.user.constant.UserType;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Profile({"local", "dev"})
public class DevService {

	private final SecurityContextService securityContextService;
	private final AuthenticationService authenticationService;

	public AuthenticationResponse createTempSession(String email, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("userType", UserType.KAKAO);
		map.put("key", "id");
		map.put("email", email);
		map.put("name", "테스트");
		map.put("hasImage", false);

		OAuth2Attributes mockAttributes = OAuth2Attributes.of(map);
		AuthenticationResult result = authenticationService.oauthSignIn(mockAttributes, request);
		securityContextService.saveToSecurityContext(result.getToken());
		return result.getResponse();
	}

}
