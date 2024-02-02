package com.ecolink.core.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.auth.domain.SocialUserCreateRequest;
import com.ecolink.core.auth.model.OAuth2Attributes;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.user.domain.Role;
import com.ecolink.core.user.domain.User;
import com.ecolink.core.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

	private final UserRepository userRepository;

	public Optional<User> getOptionalByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User getUserGraphById(Long userId) {
		return userRepository.findUserGraphById(userId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
	}

	public User createUser(OAuth2Attributes attributes, Role role) {
		return userRepository.save(User.createUser(SocialUserCreateRequest.of(attributes, role)));
	}

}
