package com.ecolink.core.auth.domain;

import com.ecolink.core.avatar.domain.AvatarCreateRequest;
import com.ecolink.core.avatar.domain.ProfilePhoto;
import com.ecolink.core.user.domain.User;

public class SocialAvatarCreateRequest implements AvatarCreateRequest {

	private final String nickname;
	private final User user;
	private final ProfilePhoto photo;

	public SocialAvatarCreateRequest(String nickname, User user, ProfilePhoto photo) {
		this.nickname = nickname;
		this.user = user;
		this.photo = photo;
	}

	public static AvatarCreateRequest of(String nickname, User user, ProfilePhoto photo) {
		return new SocialAvatarCreateRequest(nickname, user, photo);
	}

	@Override
	public String nickname() {
		return this.nickname;
	}

	@Override
	public User user() {
		return this.user;
	}

	@Override
	public ProfilePhoto photo() {
		return this.photo;
	}


}
