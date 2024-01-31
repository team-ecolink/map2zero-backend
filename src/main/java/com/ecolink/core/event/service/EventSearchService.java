package com.ecolink.core.event.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.event.dto.response.GetEventResponse;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EventSearchService {

	private final EventService eventService;
	private final StoreService storeService;

	public Page<GetEventResponse> getByStore(Long storeId, Pageable pageable) {
		Store store = storeService.getById(storeId);
		return eventService.getByStore(store.getId(), pageable)
				.map(GetEventResponse::of);
	}
}
