package com.ecolink.core.common.util;

import static com.ecolink.core.user.constant.RoleType.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.user.constant.RoleType;

public final class AuthorityUtil {

	private AuthorityUtil() {
	}

	public static boolean hasUserAuthority(UserPrincipal principal) {
		return principal != null && hasAuthority(principal, USER, MANAGER, ADMIN);
	}

	public static boolean hasAdminAuthority(UserPrincipal principal) {
		return hasAuthority(principal, ADMIN);
	}

	private static boolean hasAuthority(UserPrincipal principal, RoleType... roleTypes) {
		for (RoleType roleType : roleTypes) {
			if (principal.getAuthorities().contains(new SimpleGrantedAuthority(roleType.getAuthority())))
				return true;
		}
		return false;
	}

}
