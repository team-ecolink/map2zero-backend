package com.ecolink.core.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.auth.domain.SocialAvatarCreateRequest;
import com.ecolink.core.auth.model.OAuth2Attributes;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.avatar.service.NicknameService;
import com.ecolink.core.avatar.service.ProfilePhotoService;
import com.ecolink.core.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SignUpService {

	private final UserService userService;
	private final RoleService roleService;
	private final AvatarService avatarService;
	private final NicknameService nicknameService;
	private final ProfilePhotoService profilePhotoService;

	@Transactional
	public User signUp(OAuth2Attributes attributes) {
		User user = userService.createUser(attributes, roleService.getUserRole());
		avatarService.createAvatar(SocialAvatarCreateRequest.of(
			nicknameService.getUniqueRandomNickname(), user,
			profilePhotoService.getInitialPhoto(attributes)));
		return user;
	}

}
