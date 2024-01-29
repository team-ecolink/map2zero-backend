package com.ecolink.core.event.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.event.dto.request.EventRequest;
import com.ecolink.core.event.dto.response.EventResponse;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EventSearchService {

	private final EventService eventService;
	private final StoreService storeService;

	public List<EventResponse> getByStore(Long storeId, EventRequest request) {
		Store store = storeService.getById(storeId);
		Pageable pageable = PageRequest.of(request.getPageNo(), request.getSize());
		return eventService.getByStore(store.getId(), pageable).stream()
			.map(EventResponse::of).toList();
	}
}
