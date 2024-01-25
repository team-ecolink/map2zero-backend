package com.ecolink.core.auth.service;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityContextService {

	private final SecurityContextHolderStrategy securityContextHolderStrategy =
		SecurityContextHolder.getContextHolderStrategy();

	private final SecurityContextRepository securityContextRepository =
		new HttpSessionSecurityContextRepository();

	public void saveToSecurityContext(AbstractAuthenticationToken authenticationToken) {
		saveToSecurityContext(getRequest(), getResponse(), authenticationToken);
	}

	public void saveToSecurityContext(HttpServletRequest request, HttpServletResponse response,
		AbstractAuthenticationToken authenticationToken) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authenticationToken);
		securityContextHolderStrategy.setContext(context);
		securityContextRepository.saveContext(context, request, response);
	}

	private static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes attributes) {
			return attributes.getRequest();
		}
		return null;
	}

	private static HttpServletResponse getResponse() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes attributes) {
			return attributes.getResponse();
		}
		return null;
	}

}
