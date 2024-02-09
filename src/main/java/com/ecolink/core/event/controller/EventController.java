package com.ecolink.core.event.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecolink.core.auth.token.UserPrincipal;
import com.ecolink.core.common.config.swagger.annotation.SwaggerBody;
import com.ecolink.core.common.response.ApiCursorPageResponse;
import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.event.dto.request.AddEventRequest;
import com.ecolink.core.event.dto.request.GetEventRequest;
import com.ecolink.core.event.dto.response.GetEventListResponse;
import com.ecolink.core.event.dto.response.GetEventResponse;
import com.ecolink.core.event.service.EventAddService;
import com.ecolink.core.event.service.EventSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class EventController {

	private final EventSearchService eventSearchService;
	private final EventAddService eventAddService;

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "이벤트 리스트 조회 API", description = "이벤트 리스트 조회")
	@GetMapping("/stores/{storeId}/events")
	public ApiCursorPageResponse<GetEventListResponse, Long> eventList(
		@PathVariable("storeId") @NotNull @Positive Long storeId,
		@Valid @ParameterObject GetEventRequest request) {
		return ApiCursorPageResponse.ok(eventSearchService.getEvents(storeId, request));
	}

	@Tag(name = "${swagger.tag.store}")
	@Operation(summary = "이벤트 상세 조회 API - 인증 선택", description = "이벤트 상세 조회 - 인증 선택")
	@GetMapping("/events/{id}")
	public ApiResponse<GetEventResponse> eventDetail(
		@PathVariable("id") @NotNull @Positive Long id,
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(eventSearchService.getEvent(id, principal));
	}

	@Tag(name = "${swagger.tag.manager}")
	@Operation(summary = "이벤트 등록 API - 인증 필요",
		description = """
			이벤트 등록 - 인증 필요

			request 부분의 `content-type`을 반드시 `application/json`으로 지정해서 요청해야 합니다.
			""",
		security = {@SecurityRequirement(name = "session-token")})
	@SwaggerBody(content = @Content(
		encoding = @Encoding(name = "request", contentType = MediaType.APPLICATION_JSON_VALUE)))
	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping(value = "/m/stores/{id}/events", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<Void> addEvent(
		@Parameter(description = "추가할 이벤트 사진")
		@RequestPart(name = "images", required = false) @Size(max = 5) @Nullable List<MultipartFile> files,
		@Parameter(description = "추가할 이벤트 정보")
		@RequestPart("request") @Valid AddEventRequest request,
		@Parameter(description = "이벤트를 등록할 상점 ID") @PathVariable("id") @NotNull @Positive Long id,
		@AuthenticationPrincipal UserPrincipal principal) {
		eventAddService.addEvent(request, files, id, principal);
		return ApiResponse.ok();
	}
}
