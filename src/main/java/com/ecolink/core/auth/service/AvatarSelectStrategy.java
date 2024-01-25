package com.ecolink.core.auth.service;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.user.domain.User;

import jakarta.servlet.http.HttpServletRequest;

public interface AvatarSelectStrategy {

	Avatar select(User user, HttpServletRequest request);

}
