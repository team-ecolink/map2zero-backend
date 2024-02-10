package com.ecolink.core.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.response.StoreTrendResponse;
import com.ecolink.core.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreTrendService {

	private final StoreRepository storeRepository;
	private final StorePhotoService storePhotoService;

	public List<StoreTrendResponse> getTrendyStores() {
		List<Store> stores = storeRepository.findTop10ByOrderByBookmarkCntDesc();

		return stores.stream()
			.map(store -> StoreTrendResponse.of(store, storePhotoService.getMainPhoto(store)))
			.toList();
	}
}
