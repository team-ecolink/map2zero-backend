package com.ecolink.core.store.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.response.StoreDetailResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreSearchService {

	private final StoreService storeService;

	public StoreDetailResponse getStoreDetailPage(Long id, Long avatarId) {
		Store store = storeService.getStoreGraphById(id);
		// 북마크 부분은 나중에 처리
		Boolean isBookmarked = null;
		if(avatarId != null) {
			return StoreDetailResponse.of(store, isBookmarked);
		}
		return StoreDetailResponse.of(store, isBookmarked);
	}



}
