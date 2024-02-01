package com.ecolink.core.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.domain.ProfilePhoto;
import com.ecolink.core.avatar.dto.request.UpdateNicknameRequest;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.DuplicatedNicknameException;
import com.ecolink.core.common.error.exception.FileUploadException;
import com.ecolink.core.file.constant.FilePath;
import com.ecolink.core.file.service.SinglePhotoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AvatarInfoService {

	private final AvatarService avatarService;
	private final NicknameService nicknameService;
	private final ProfilePhotoService profilePhotoService;
	private final SinglePhotoService singlePhotoService;

	@Transactional
	public void updateNickname(UpdateNicknameRequest request, Long avatarId) {
		String nickname = request.getNickname();
		if (nicknameService.isNicknameInUse(nickname))
			throw new DuplicatedNicknameException(ErrorCode.DUPLICATED_NICKNAME);

		Avatar avatar = avatarService.getById(avatarId);
		avatar.updateNickname(nickname);
	}

	@Transactional
	public void updatePhoto(Long avatarId, MultipartFile file, boolean defaultPhoto) {
		Avatar avatar = avatarService.getByIdWithPhoto(avatarId);
		if (defaultPhoto) {
			singlePhotoService.changePhoto(avatar, profilePhotoService.getDefaultPhoto());
			return;
		}
		if (file == null)
			throw new FileUploadException(ErrorCode.IMAGE_FILE_IS_NULL);

		singlePhotoService.changePhoto(file, avatar, ProfilePhoto::new, FilePath.PROFILE_PHOTO);
	}

}
