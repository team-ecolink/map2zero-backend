package com.ecolink.core.auth.token;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.manager.domain.Manager;
import com.ecolink.core.user.domain.User;

public class AuthenticationToken extends AbstractAuthenticationToken {

	private final UserPrincipal principal;

	private AuthenticationToken(UserPrincipal principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
	}

	public static AuthenticationToken of(User user, Avatar avatar, Set<? extends GrantedAuthority> authorities) {
		return of(user, avatar, authorities, null);
	}

	public static AuthenticationToken of(User user, Avatar avatar, Set<? extends GrantedAuthority> authorities,
		Manager manager) {
		return new AuthenticationToken(UserPrincipal.of(user, avatar, authorities, manager), authorities);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AuthenticationToken that))
			return false;
		if (!super.equals(o))
			return false;

		return principal.equals(that.principal);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + principal.hashCode();
		return result;
	}

}
