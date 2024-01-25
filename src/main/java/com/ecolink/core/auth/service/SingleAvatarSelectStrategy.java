package com.ecolink.core.auth.service;

import org.springframework.stereotype.Service;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.user.domain.User;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SingleAvatarSelectStrategy implements AvatarSelectStrategy {

	@Override
	public Avatar select(User user, HttpServletRequest request) {
		return user.getAvatars().get(0);
	}
}
