package com.ecolink.core.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.domain.AvatarCreateRequest;
import com.ecolink.core.avatar.repository.AvatarRepository;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AvatarService {

	private final AvatarRepository avatarRepository;

	public Avatar getById(Long avatarId) {
		return avatarRepository.findById(avatarId).orElseThrow(
			() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_FOUND));
	}

	public Avatar getUserGraphById(Long avatarId) {
		return avatarRepository.findUserGraphById(avatarId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_FOUND));
	}

	@Transactional
	public Avatar createAvatar(AvatarCreateRequest request) {
		return avatarRepository.save(Avatar.of(request));
	}
}
