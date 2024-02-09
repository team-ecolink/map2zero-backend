package com.ecolink.core.event.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.dto.CursorPage;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.ManagerForbiddenException;
import com.ecolink.core.event.constant.EventStatus;
import com.ecolink.core.event.domain.Event;
import com.ecolink.core.event.dto.request.GetEventRequest;
import com.ecolink.core.event.dto.request.GetManagerEventRequest;
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

	public GetEventResponse getEvent(Long id, UserPrincipal principal) {
		Event event = eventService.getGraphById(id);
		boolean managerOf = principal != null && principal.isManagerOf(event.getStore().getId());
		if (!managerOf && event.getStatus() == EventStatus.INACTIVE) {
			throw new ManagerForbiddenException(ErrorCode.INACTIVE_EVENT_FORBIDDEN);
		}
		return GetEventResponse.of(event, managerOf);
	}

	public CursorPage<GetEventListResponse, Long> getEventsForManager(Long storeId, GetManagerEventRequest request,
		UserPrincipal principal) {
		Store store = storeService.getById(storeId);
		if (!principal.isManagerOf(store.getId())) {
			throw new ManagerForbiddenException(ErrorCode.NOT_MANAGER_OF_STORE);
		}
		return CursorPage.of(eventService.getByStoreAndStatusForManager(store.getId(), request), request.getSize(),
			GetEventListResponse::getId);
	}
}
