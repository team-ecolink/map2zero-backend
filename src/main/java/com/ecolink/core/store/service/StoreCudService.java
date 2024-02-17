package com.ecolink.core.store.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.util.AuthorityUtil;
import com.ecolink.core.file.constant.FilePath;
import com.ecolink.core.file.service.MultiPhotoService;
import com.ecolink.core.store.domain.StorePhoto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreCudService {

	private final StoreService storeService;
	private final MultiPhotoService multiPhotoService;

	@Transactional
	public void changeStorePhotos(Long storeId, List<MultipartFile> images, UserPrincipal principal) {
		if (!AuthorityUtil.hasAdminAuthority(principal))
			throw new AccessDeniedException("권한이 없습니다. 어드민만 접근이 가능합니다.");
		multiPhotoService.changePhotos(images, storeService.getByIdWithPhotos(storeId), StorePhoto::of,
			FilePath.STORE_PHOTO);
	}

}
