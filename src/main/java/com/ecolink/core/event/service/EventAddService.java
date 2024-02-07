package com.ecolink.core.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.PhotoLimitExceededException;
import com.ecolink.core.event.domain.Event;
import com.ecolink.core.event.domain.EventPhoto;
import com.ecolink.core.event.dto.request.AddEventRequest;
import com.ecolink.core.event.repository.EventRepository;
import com.ecolink.core.file.constant.FilePath;
import com.ecolink.core.file.service.MultiPhotoService;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EventAddService {

	private final EventRepository eventRepository;
	private final StoreService storeService;
	private final MultiPhotoService multiPhotoService;

	@Transactional
	public void addEvent(AddEventRequest request, List<MultipartFile> files, Long storeId) {
		Store store = storeService.getById(storeId);
		Event event = new Event(request, store);
		eventRepository.save(event);

		if (files != null && files.size() > 5)
			throw new PhotoLimitExceededException(ErrorCode.PHOTO_LIMIT_EXCEEDED);
		if (files != null)
			multiPhotoService.addPhotos(files, event, EventPhoto::of, FilePath.EVENT_PHOTO);
	}
}
