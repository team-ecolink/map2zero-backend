package com.ecolink.core.auth.token;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.user.constant.UserType;
import com.ecolink.core.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPrincipal implements Serializable {

	// User
	private final Long userId;
	private final String email;
	private final UserType userType;
	private boolean locked;
	private boolean expired;
	private boolean activated;
	private LocalDateTime lastPwChangedDate;
	private String name;
	private boolean adAgreed;
	private boolean analysisAgreed;
	private String fcmToken;
	private LocalDateTime lastLoginDate;

	// Avatar
	private final Long avatarId;
	private String nickname;

	// Role
	private final Set<SimpleGrantedAuthority> authorities;

	public static UserPrincipal of(User user, Avatar avatar, Set<SimpleGrantedAuthority> authoritySet) {
		return UserPrincipal.builder()
			.userId(user.getId())
			.email(user.getEmail())
			.locked(user.isLocked())
			.expired(user.isExpired())
			.activated(user.isActivated())
			.lastPwChangedDate(user.getLastPwChangedDate())
			.name(user.getName())
			.userType(user.getUserType())
			.adAgreed(user.isAdAgreed())
			.analysisAgreed(user.isAnalysisAgreed())
			.fcmToken(user.getFcmToken())
			.avatarId(avatar.getId())
			.nickname(avatar.getNickname())
			.authorities(authoritySet)
			.build();
	}

}
