package com.ecolink.core.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.event.constant.EventStatus;
import com.ecolink.core.event.domain.Event;
import com.ecolink.core.event.dto.request.GetEventRequest;
import com.ecolink.core.event.dto.request.GetManagerEventRequest;
import com.ecolink.core.event.dto.response.GetEventListResponse;
import com.ecolink.core.event.repository.EventJpaRepository;
import com.ecolink.core.event.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EventService {

	private final EventRepository eventRepository;
	private final EventJpaRepository eventJpaRepository;

	public Event getById(Long id) {
		return eventRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException(ErrorCode.EVENT_NOT_FOUND));
	}

	public List<GetEventListResponse> getByStoreAndStatus(Long storeId, GetEventRequest request) {
		return eventJpaRepository.findEventByStoreAndStatus(storeId, request, EventStatus.ACTIVE);
	}

	public Event getGraphById(Long id) {
		return eventRepository.findGraphById(id).orElseThrow(
			() -> new EntityNotFoundException(ErrorCode.EVENT_NOT_FOUND));
	}

	public List<GetEventListResponse> getByStoreAndStatusForManager(Long storeId, GetManagerEventRequest request) {
		return eventJpaRepository.findEventByStoreAndStatus(storeId, request, request.getStatus());
	}
}
