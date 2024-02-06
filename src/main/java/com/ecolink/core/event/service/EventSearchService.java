package com.ecolink.core.event.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.dto.CursorPage;
import com.ecolink.core.event.dto.request.GetEventRequest;
import com.ecolink.core.event.dto.response.GetEventListResponse;
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

	public CursorPage<GetEventListResponse, Long> getEvents(Long storeId, GetEventRequest request) {
		Store store = storeService.getById(storeId);
		return CursorPage.of(eventService.getByStoreAndStatus(store.getId(), request), request.getSize(),
			GetEventListResponse::getId);
	}

	public GetEventResponse getEvent(Long id, boolean isManager) {
		return GetEventResponse.of(eventService.getGraphById(id), isManager);
	}
}
