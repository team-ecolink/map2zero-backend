package com.ecolink.core.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.dto.request.UpdateNicknameRequest;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.DuplicatedNicknameException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AvatarInfoService {

	private final AvatarService avatarService;
	private final NicknameService nicknameService;

	@Transactional
	public void updateNickname(UpdateNicknameRequest request, Long avatarId) {
		String nickname = request.getNickname();
		if(nicknameService.isNicknameInUse(nickname))
			throw new DuplicatedNicknameException(ErrorCode.DUPLICATED_NICKNAME);

		Avatar avatar = avatarService.getById(avatarId);
		avatar.updateNickname(nickname);
	}
}
