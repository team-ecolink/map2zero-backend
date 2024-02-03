package com.ecolink.core.tag.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.core.common.response.ApiResponse;
import com.ecolink.core.tag.dto.request.GetTagRequest;
import com.ecolink.core.tag.dto.response.GetTagResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/tags")
public class TagController {

	private final TagService tagService;

	@Tag(name = "${swagger.tag.tag}")
	@Operation(summary = "태그 정보 조회 API",
		description = "태그 정보 조회")
	@GetMapping
	public ApiResponse<List<GetTagResponse>> myPageInfo(
		@Valid @ParameterObject GetTagRequest request) {
		return ApiResponse.ok(tagService.getTags(request));
	}

}
