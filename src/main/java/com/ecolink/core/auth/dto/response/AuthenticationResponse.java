package com.ecolink.core.auth.dto.response;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.user.constant.UserType;
import com.ecolink.core.user.domain.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthenticationResponse {

	private final String email;
	private final String nickname;
	private final ImageFile photo;
	private final Boolean isNewUser;
	private final UserType type;

	private AuthenticationResponse(String email, UserType type, String nickname, ImageFile photo,
		boolean isNewUser) {
		this.email = email;
		this.type = type;
		this.nickname = nickname;
		this.photo = photo;
		this.isNewUser = isNewUser;
	}

	public static AuthenticationResponse of(User user, Avatar avatar, boolean newUser) {
		return new AuthenticationResponse(user.getEmail(), user.getUserType(), avatar.getNickname(),
			avatar.getPhoto().getFile(), newUser);
	}

}
