package com.ecolink.core.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.auth.model.OAuth2Attributes;
import com.ecolink.core.avatar.domain.ProfilePhoto;
import com.ecolink.core.avatar.repository.ProfilePhotoRepository;
import com.ecolink.core.common.domain.ImageFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfilePhotoService {

	private static final String DEFAULT_PROFILE_PHOTO_URL = "https://map2zero-bucket.s3.ap-northeast-2.amazonaws.com/public/static/map2zero_default_photo.jpeg";
	private final ProfilePhotoRepository profilePhotoRepository;

	@Transactional
	public ProfilePhoto getInitialPhoto(OAuth2Attributes attributes) {
		return profilePhotoRepository.save(new ProfilePhoto(getImageFile(attributes)));
	}

	private ImageFile getImageFile(OAuth2Attributes attributes) {
		if (attributes.hasImage())
			return ImageFile.externalImage(attributes.getProfileImage());
		return ImageFile.externalImage(DEFAULT_PROFILE_PHOTO_URL);
	}

	public ProfilePhoto getDefaultPhoto() {
		return new ProfilePhoto(ImageFile.externalImage(DEFAULT_PROFILE_PHOTO_URL));
	}

}
