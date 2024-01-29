package com.ecolink.core.event.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.common.util.AuthorityUtil;
import com.ecolink.core.event.dto.request.EventRequest;
import com.ecolink.core.event.dto.response.EventResponse;
import com.ecolink.core.event.service.EventSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class EventController {

	private final EventSearchService eventSearchService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "이벤트 리스트 조회 API", description = "이벤트 리스트 조회")
	@GetMapping("/stores/{storeId}/events")
	public ApiResponse<List<EventResponse>> eventList(
		@PathVariable("storeId") Long storeId,
		@ParameterObject @Valid EventRequest request) {
		return ApiResponse.ok(eventSearchService.getByStore(storeId, request));
	}
}
