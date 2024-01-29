package com.ecolink.core.event.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.event.domain.Event;
import com.ecolink.core.event.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EventService {

	private final EventRepository eventRepository;

	public Event getById(Long id) {
		return eventRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException(ErrorCode.EVENT_NOT_FOUND));
	}

	public Page<Event> getByStore(Long storeId, Pageable pageable) {
		return eventRepository.findAllByStore(storeId, pageable);
	}
}
