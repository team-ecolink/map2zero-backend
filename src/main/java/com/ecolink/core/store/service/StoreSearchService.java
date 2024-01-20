package com.ecolink.core.store.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.avatar.service.AvatarService;
import com.ecolink.core.bookmark.repository.BookmarkRepository;
import com.ecolink.core.store.domain.SearchHistory;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StoreProduct;
import com.ecolink.core.store.dto.StoreProductDto;
import com.ecolink.core.store.dto.request.StoreSearchRequest;
import com.ecolink.core.store.dto.response.StoreSearchDto;
import com.ecolink.core.store.event.SaveSearchHistoryEvent;
import com.ecolink.core.store.repository.StoreJpaRepository;
import com.ecolink.core.store.repository.StorePhotoRepository;
import com.ecolink.core.store.repository.StoreProductJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreSearchService {

	private final StoreJpaRepository storeJpaRepository;
	private final StoreProductJpaRepository storeProductJpaRepository;
	private final StorePhotoRepository storePhotoRepository;
	private final BookmarkRepository bookmarkRepository;
	private final AvatarService avatarService;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional
	public List<StoreSearchDto> searchStores(StoreSearchRequest request, Long userId, Long cursorId,
		int pageSize) {

		String keyword = request.getKeyword();
		String type = request.getType();
		Avatar avatar = avatarService.getById(userId);
		Page<Store> stores;
		List<StoreSearchDto> result;

		// 메인 로직
		if (type.equals("매장")) {
			stores = storeJpaRepository.findPageByNameContainingOrderByBookmarkCntDesc(keyword, cursorId, pageSize);
			result = stores.stream()
				.flatMap(store -> {
					List<StoreProduct> products = storeProductJpaRepository.findTop3ByStoreOrderByProductName(
						store);
					List<StoreProductDto> storeProductDtos = products.stream()
						.map(storeProduct -> new StoreProductDto(storeProduct.getId(),
							storeProduct.getProduct().getName()))
						.toList();

					String mainPhotoUrl = storePhotoRepository.getStorePhotoByGivenOrderAndStore(0, store)
						.getFile()
						.getUrl();
					boolean isBookmarked = bookmarkRepository.existsBookmarkByAvatarAndStore(avatar, store);
					return Stream.of(StoreSearchDto.of(store, mainPhotoUrl, storeProductDtos, isBookmarked));
				})
				.toList();
		} else {
			stores = storeJpaRepository.findPageByProductNameOrderByBookmarkCntDesc(keyword, cursorId, pageSize);
			result = stores.stream()
				.flatMap(store -> {
					List<StoreProduct> products = storeProductJpaRepository.findTop3ByStoreOrderByProductName(
						keyword, store);
					List<StoreProductDto> storeProductDtos = products.stream()
						.map(storeProduct -> new StoreProductDto(storeProduct.getId(),
							storeProduct.getProduct().getName()))
						.toList();

					String mainPhotoUrl = storePhotoRepository.getStorePhotoByGivenOrderAndStore(0, store)
						.getFile()
						.getUrl();
					boolean isBookmarked = bookmarkRepository.existsBookmarkByAvatarAndStore(avatar, store);
					return Stream.of(StoreSearchDto.of(store, mainPhotoUrl, storeProductDtos, isBookmarked));
				})
				.toList();
		}

		// 부가 로직
		SearchHistory searchHistory = new SearchHistory(keyword, avatar);
		eventPublisher.publishEvent(new SaveSearchHistoryEvent(this, searchHistory));

		return result;
	}
}
