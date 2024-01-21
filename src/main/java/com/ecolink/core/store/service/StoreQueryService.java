package com.ecolink.core.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.review.dto.ReviewDto;
import com.ecolink.core.review.service.ReviewService;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.response.StoreDetailResponse;
import com.ecolink.core.store.dto.response.StoreInfoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreQueryService {

	private final StoreService storeService;
	private final ReviewService reviewService;

	public StoreDetailResponse getStoreDetailPage(Long id, Long avatarId) {
		Store store = storeService.getById(id);
		List<ReviewDto> reviewList = reviewService.getAllByStore(store, avatarId);
		// 북마크 부분은 나중에 처리
		Boolean isBookmarked = null;
		if(avatarId != null) {
			return StoreDetailResponse.of(store, reviewList, isBookmarked);
		}
		return StoreDetailResponse.of(store, reviewList, isBookmarked);
	}

	public StoreInfoResponse getStoreInfo(Long id) {
		Store store = storeService.getById(id);
		return StoreInfoResponse.of(store);
	}


}
