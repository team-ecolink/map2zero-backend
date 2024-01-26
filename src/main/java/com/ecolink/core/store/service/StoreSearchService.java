package com.ecolink.core.store.service;

import java.util.List;

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

	private final StoreJpaRepository storeJpaRepository;
	private final StoreProductService storeProductService;
	private final ApplicationEventPublisher eventPublisher;

	public CursorPage<StoreSearchDto, Long> searchStores(StoreSearchRequest request, Long avatarId) {
		List<StoreSearchDto> storeSearchDtos = storeJpaRepository.findStoresByKeyword(request, avatarId);

		// 매장 제품 3개만 잘라서 넣기
		storeProductService.addTop3ProductsToDto(request, storeSearchDtos);
		
		eventPublisher.publishEvent(new StoreSearchEvent(request.getKeyword(), avatarId));

		return CursorPage.of(storeSearchDtos, request.getSize(), StoreSearchDto::getId);
	}
}
