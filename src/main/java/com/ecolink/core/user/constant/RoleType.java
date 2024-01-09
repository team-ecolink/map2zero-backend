package com.ecolink.core.user.constant;

import lombok.Getter;

@Getter
public enum RoleType {

	GUEST("ROLE_GUEST"),
	USER("ROLE_USER"),
	MANAGER("ROLE_MANAGER"),
	ADMIN("ROLE_ADMIN");

	private final String authority;

	RoleType(String authority) {
		this.authority = authority;
	}

}
