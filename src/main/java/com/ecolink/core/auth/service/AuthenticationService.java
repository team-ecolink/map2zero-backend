package com.ecolink.core.auth.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.auth.dto.response.AuthenticationResponse;
import com.ecolink.core.auth.model.OAuth2Attributes;
import com.ecolink.core.auth.token.AuthenticationToken;
import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.DuplicatedEmailException;
import com.ecolink.core.user.domain.User;
import com.ecolink.core.auth.dto.AuthenticationResult;
import com.ecolink.core.user.service.SignUpService;
import com.ecolink.core.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthenticationService {

	private final UserService userService;
	private final SignUpService signUpService;
	private final AvatarSelectStrategy avatarSelectStrategy;

	@Transactional
	public AuthenticationResult oauthSignIn(OAuth2Attributes attributes, HttpServletRequest request) {
		Optional<User> optionalUser = userService.getOptionalByEmail(attributes.getEmail());
		boolean isNewUser = optionalUser.isEmpty();
		User user = optionalUser.orElseGet(() -> signUpService.signUp(attributes));

		if (!isNewUser && !user.isUserOf(attributes.getUserType()))
			throw new DuplicatedEmailException(ErrorCode.EMAIL_IS_REGISTER_WITH_ANOTHER_PROVIDER,
				attributes.getUserType(), user.getUserType());

		Avatar avatar = avatarSelectStrategy.select(user, request);

		return new AuthenticationResult(
			AuthenticationToken.of(user, avatar, Set.of(user.getRole().toGrantedAuthority())),
			AuthenticationResponse.of(user, avatar, isNewUser));
	}
}
