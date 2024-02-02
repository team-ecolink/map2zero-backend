package com.ecolink.core.auth.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.ecolink.core.auth.service.JsonResponseMaker;
import com.ecolink.core.auth.token.UserPrincipal;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class JsonLogoutSuccessHandler implements LogoutSuccessHandler {

	private final JsonResponseMaker jsonResponseMaker;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
		log.info("User signed out: {}", principal.getEmail());
		jsonResponseMaker.mapToJson(response, principal.getEmail());
	}

}
