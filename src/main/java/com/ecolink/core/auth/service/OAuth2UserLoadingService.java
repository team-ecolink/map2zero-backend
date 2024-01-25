package com.ecolink.core.auth.service;

import java.util.List;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ecolink.core.auth.model.OAuth2Attributes;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.InvalidProviderException;
import com.ecolink.core.user.constant.UserType;

@Service
public class OAuth2UserLoadingService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		String userNameAttributeName = userRequest.getClientRegistration()
			.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		UserType type = UserType.fromString(registrationId)
			.orElseThrow(() -> new InvalidProviderException(ErrorCode.UNDEFINED_PROVIDER));

		OAuth2Attributes attributes = type.extract(userNameAttributeName, oAuth2User.getAttributes());

		return new DefaultOAuth2User(List.of(), attributes.convertToMap(), "email");
	}

}
