package com.ecolink.core.map.controller;

import com.ecolink.core.map.service.StoreMapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class StoreMapController {

	private final StoreMapService themeService;

	public StoreMapController(StoreMapService themeService) {
		this.themeService = themeService;
	}

	@Tag(name = "${swagger.map}")
	@Operation(summary = "스마트서울맵 데이터 조회 API", description = "테마 내 모든 콘텐츠 리스트 API")
	@GetMapping
	public void getContentsListAll(@RequestParam("themeId") String themeId) {
		themeService.saveContentsListAll(themeId);
	}
}
