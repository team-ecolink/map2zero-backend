package com.ecolink.core.map.controller;

import com.ecolink.core.map.util.ContentDetailFetcher;
import com.ecolink.core.map.util.ContentListFetcher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
public class StoreMapController {

	private final ContentListFetcher contentListFetcher;
	private final ContentDetailFetcher contentDetailFetcher;
	public StoreMapController(ContentListFetcher contentListFetcher, ContentDetailFetcher contentDetailFetcher) {
		this.contentListFetcher = contentListFetcher;
		this.contentDetailFetcher = contentDetailFetcher;
	}

	@Tag(name = "${swagger.map-data}")
	@Operation(summary = "스마트서울맵 데이터 조회 API", description = "테마 내 모든 콘텐츠 리스트 API")
	@GetMapping("/list")
	public void getContentsListAll() {
		contentListFetcher.fetchDataAndStore();

	}

	@Tag(name = "${swagger.map-data}")
	@Operation(summary = "스마트서울맵 데이터 조회 API", description = "테마 내 모든 콘텐츠 상세 정보 API")
	@GetMapping("/data")
	public void getContentsDetailAll() {
		contentDetailFetcher.fetchAndStoreContentDetail();
	}

}
