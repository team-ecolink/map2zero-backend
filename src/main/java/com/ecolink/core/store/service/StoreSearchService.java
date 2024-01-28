package com.ecolink.core.store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.response.StoreDetailResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.dto.CursorPage;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.ecolink.core.store.event.StoreSearchEvent;
import com.ecolink.core.store.repository.StoreJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreSearchService {

	private final StoreService storeService;
  private final StoreJpaRepository storeJpaRepository;
	private final StoreProductService storeProductService;
	private final ApplicationEventPublisher eventPublisher;

	public StoreDetailResponse getStoreDetailPage(Long id, Long avatarId) {
		Store store = storeService.getStoreGraphById(id);
		// 북마크 부분은 나중에 처리
		Boolean isBookmarked = null;
		if(avatarId != null) {
			return StoreDetailResponse.of(store, isBookmarked);
		}
		return StoreDetailResponse.of(store, isBookmarked);
	}

	public CursorPage<StoreSearchDto, Long> searchStores(StoreSearchRequest request, Long avatarId) {

		List<StoreSearchDto> storeSearchDtos = storeJpaRepository.findStoresByKeyword(request, avatarId);

		storeProductService.processAndLimitTop3Products(request, storeSearchDtos);

		eventPublisher.publishEvent(new StoreSearchEvent(request.getKeyword(), avatarId));

		return CursorPage.of(storeSearchDtos, request.getSize(), StoreSearchDto::getId);
	}
}
