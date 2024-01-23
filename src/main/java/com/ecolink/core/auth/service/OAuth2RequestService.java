package com.ecolink.core.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Service;

import com.ecolink.core.auth.dto.response.OAuth2StateResponse;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.InvalidProviderException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class OAuth2RequestService {

	private final DefaultOAuth2AuthorizationRequestResolver oAuth2AuthorizationRequestResolver;
	private final ClientRegistrationRepository clientRegistrationRepository;
	private final HttpSessionOAuth2AuthorizationRequestRepository oauth2Repository;

	public OAuth2RequestService(ClientRegistrationRepository clientRegistrationRepository,
		@Value("${api.prefix}") String apiPrefix) {
		this.clientRegistrationRepository = clientRegistrationRepository;
		this.oAuth2AuthorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(
			clientRegistrationRepository, apiPrefix + "oauth2/authorization");
		this.oauth2Repository = new HttpSessionOAuth2AuthorizationRequestRepository();
	}


	public OAuth2StateResponse createRequest(HttpServletRequest request, HttpServletResponse response, String provider) {
		if(clientRegistrationRepository.findByRegistrationId(provider) == null)
			throw new InvalidProviderException(ErrorCode.INVALID_PROVIDER);

		// OAuth 인가 요청을 생성
		OAuth2AuthorizationRequest oAuth2AuthorizationRequest = oAuth2AuthorizationRequestResolver.resolve(request,
			provider);
		// 요청을 임시 Session 에 저장
		oauth2Repository.saveAuthorizationRequest(oAuth2AuthorizationRequest, request, response);

		return OAuth2StateResponse.of(oAuth2AuthorizationRequest);
	}

}
