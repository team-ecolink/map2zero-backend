package com.ecolink.core.avatar.domain;

import com.ecolink.core.user.domain.User;

public interface AvatarCreateRequest {

	String nickname();

	User user();

	ProfilePhoto photo();

}
