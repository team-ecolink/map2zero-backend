package com.ecolink.core.event.controller;

import com.ecolink.core.common.response.ApiPageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.event.dto.response.GetEventResponse;
import com.ecolink.core.event.service.EventSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class EventController {

	private final EventSearchService eventSearchService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "이벤트 리스트 조회 API", description = "이벤트 리스트 조회")
	@GetMapping("/stores/{storeId}/events")
	public ApiPageResponse<GetEventResponse> eventList(
		@PathVariable("storeId") Long storeId,
		@Parameter(hidden = true)
		@PageableDefault(size = 10) Pageable pageable) {
		return ApiPageResponse.ok(eventSearchService.getByStore(storeId, pageable));
	}
}
