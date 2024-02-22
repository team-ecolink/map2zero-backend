package com.ecolink.core.auth.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ecolink.core.auth.dto.AuthenticationResult;
import com.ecolink.core.auth.model.OAuth2Attributes;
import com.ecolink.core.auth.service.AuthenticationService;
import com.ecolink.core.auth.service.JsonResponseMaker;
import com.ecolink.core.auth.service.SecurityContextService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

	private final SecurityContextService securityContextService;
	private final AuthenticationService authenticationService;
	private final JsonResponseMaker jsonResponseMaker;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken)authentication;

		OAuth2Attributes attributes = OAuth2Attributes.of(oauth2Token.getPrincipal().getAttributes());
		AuthenticationResult result = authenticationService.oauthSignIn(attributes, request);
		log.info("User signed in: {}", result.getResponse());
		securityContextService.saveToSecurityContext(result.getToken());
		jsonResponseMaker.mapToJson(response, result.getResponse());
	}

}
