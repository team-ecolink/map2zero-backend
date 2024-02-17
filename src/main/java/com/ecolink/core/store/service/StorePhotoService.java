package com.ecolink.core.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StorePhoto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StorePhotoService {

	public StorePhoto getMainPhoto(Store store) {
		List<StorePhoto> photos = store.getPhotos();

		return photos.stream()
			.filter(photo -> photo.getGivenOrder() == 0)
			.findFirst()
			.orElse(null);
	}
}
