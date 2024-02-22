package com.ecolink.core.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.dto.response.GetMyPageResponse;
import com.ecolink.core.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageService {

	private final AvatarService avatarService;

	public GetMyPageResponse getMyPageInfo(Long avatarId) {
		Avatar avatar = avatarService.getUserGraphById(avatarId);
		User user = avatar.getUser();
		return GetMyPageResponse.of(user, avatar, user.isManager());
	}

}
