package com.ecolink.core.store.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.bookmark.service.BookmarkService;
import com.ecolink.core.store.domain.SearchHistory;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StoreProduct;
import com.ecolink.core.store.dto.StoreProductDto;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.ecolink.core.store.event.SaveSearchHistoryEvent;
import com.ecolink.core.store.repository.StoreJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreSearchService {

	private final StoreJpaRepository storeJpaRepository;
	private final AvatarService avatarService;
	private final BookmarkService bookmarkService;
	private final StorePhotoService storePhotoService;
	private final StoreProductService storeProductService;
	private final ApplicationEventPublisher eventPublisher;

	public List<StoreSearchDto> searchStores(StoreSearchRequest request, Long avatarId) {

		Avatar avatar = (avatarId != null) ? avatarService.getById(avatarId) : null;
		Page<Store> stores = storeJpaRepository.findStoresByKeywordContainingOrderByBookmarkCntDesc(request);

		List<StoreSearchDto> result = stores.stream().map(store -> {
			boolean isBookmarked = bookmarkService.isBookmarked(avatar, store);
			String mainPhotoUrl = storePhotoService.getStoreMainPhotoUrl(store);
			List<StoreProduct> storeProducts = storeProductService.findTop3StoreProductsOrderByProductName(
				request.getType(), request.getKeyword(), store);
			List<StoreProductDto> storeProductDtos = storeProductService.getStoreProductDtos(storeProducts);
			return StoreSearchDto.of(store, mainPhotoUrl, storeProductDtos, isBookmarked);
		}).toList();

		SearchHistory searchHistory = new SearchHistory(request.getKeyword(), avatar);
		eventPublisher.publishEvent(new SaveSearchHistoryEvent(this, searchHistory));

		return result;
	}
}
