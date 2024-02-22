package com.ecolink.core.admin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.admin.dto.response.GetCurationResponse;
import com.ecolink.core.admin.service.CurationService;
import com.ecolink.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/curations")
public class CurationController {

	private final CurationService curationService;

	@Tag(name = "${swagger.tag.home}")
	@Operation(summary = "큐레이션 조회 API",
		description = "큐레이션 조회")
	@GetMapping
	public ApiResponse<List<GetCurationResponse>> getTodayProducts() {
		return ApiResponse.ok(curationService.getCurations());
	}

}
