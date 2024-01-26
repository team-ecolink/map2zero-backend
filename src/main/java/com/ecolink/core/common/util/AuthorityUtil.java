package com.ecolink.core.common.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.user.constant.RoleType;

public final class AuthorityUtil {

	private AuthorityUtil() {
	}

	public static boolean hasUserAuthority(UserPrincipal principal) {
		return principal != null && hasAuthority(principal, RoleType.USER, RoleType.MANAGER, RoleType.ADMIN);
	}

	private static boolean hasAuthority(UserPrincipal principal, RoleType... roleTypes) {
		for (RoleType roleType : roleTypes) {
			if (principal.getAuthorities().contains(new SimpleGrantedAuthority(roleType.getAuthority())))
				return true;
		}
		return false;
	}

}
