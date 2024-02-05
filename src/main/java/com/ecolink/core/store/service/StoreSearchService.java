package com.ecolink.core.store.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.bookmark.dto.response.StoreInfoDto;
import com.ecolink.core.bookmark.service.BookmarkService;
import com.ecolink.core.common.dto.CursorPage;
import com.ecolink.core.product.service.StoreProductService;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.StoreDetailResponse;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.ecolink.core.store.event.StoreSearchEvent;
import com.ecolink.core.store.repository.StoreJpaRepository;
import com.ecolink.core.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreSearchService {

	private final StoreService storeService;
	private final BookmarkService bookmarkService;
	private final StoreRepository storeRepository;
	private final StoreJpaRepository storeJpaRepository;
	private final StoreProductService storeProductService;
	private final ApplicationEventPublisher eventPublisher;

	public StoreDetailResponse getStoreDetailPage(Long storeId, Long avatarId) {
		return StoreDetailResponse.of(storeService.getStoreGraphById(storeId),
			bookmarkService.existsBookmark(avatarId, storeId));
	}

	public CursorPage<StoreSearchDto, Long> searchStores(StoreSearchRequest request, Long avatarId) {
		List<StoreSearchDto> storeSearchDtos = storeJpaRepository.findStoresByKeyword(request, avatarId);

		storeProductService.processAndLimitTop3Products(request, storeSearchDtos);

		eventPublisher.publishEvent(new StoreSearchEvent(request.getKeyword(), avatarId));

		return CursorPage.of(storeSearchDtos, request.getSize(), StoreSearchDto::getId);
	}

	public List<StoreInfoDto> getTop10PopularStores() {
		List<Store> stores = storeRepository.findTop10ByOrderByBookmarkCntDesc();
		return stores.stream().map(StoreInfoDto::of).toList();
	}

}
