package com.ecolink.core.event.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.common.response.ApiCursorPageResponse;
import com.ecolink.core.event.dto.request.GetEventRequest;
import com.ecolink.core.event.dto.response.GetEventListResponse;
import com.ecolink.core.event.service.EventSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class EventController {

	private final EventSearchService eventSearchService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "이벤트 리스트 조회 API", description = "이벤트 리스트 조회")
	@GetMapping("/stores/{storeId}/events")
	public ApiCursorPageResponse<GetEventListResponse, Long> eventList(
		@PathVariable("storeId") @NotNull @Positive Long storeId,
		@Valid @ParameterObject GetEventRequest request) {
		return ApiCursorPageResponse.ok(eventSearchService.getEvents(storeId, request));
	}
}
