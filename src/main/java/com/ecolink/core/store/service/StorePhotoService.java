package com.ecolink.core.store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.repository.StorePhotoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StorePhotoService {

	private final StorePhotoRepository storePhotoRepository;

	public String getStoreMainPhotoUrl(Store store) {
		return storePhotoRepository.getStorePhotoByGivenOrderAndStore(0, store)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.STORE_MAIN_PHOTO_NOT_FOUND))
			.getFile().getUrl();
	}
}
