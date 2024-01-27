package com.ecolink.core.auth.domain;

import com.ecolink.core.auth.model.OAuth2Attributes;
import com.ecolink.core.user.constant.Password;
import com.ecolink.core.user.constant.UserType;
import com.ecolink.core.user.domain.Role;
import com.ecolink.core.user.domain.UserCreateRequest;

public class SocialUserCreateRequest implements UserCreateRequest {

	private final String name;
	private final String email;
	private final UserType userType;
	private final Role role;

	public SocialUserCreateRequest(String name, String email, UserType userType, Role role) {
		this.name = name;
		this.email = email;
		this.userType = userType;
		this.role = role;
	}

	public static SocialUserCreateRequest of(OAuth2Attributes attributes, Role role) {
		return new SocialUserCreateRequest(
			attributes.getName(),
			attributes.getEmail(),
			attributes.getUserType(),
			role
		);
	}

	@Override
	public String email() {
		return this.email;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public UserType userType() {
		return this.userType;
	}

	@Override
	public Role role() {
		return this.role;
	}

	@Override
	public Password password() {
		return null;
	}
}
