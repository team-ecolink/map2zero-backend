package com.ecolink.core.map.controller;

import com.ecolink.core.map.util.ContentDetailFetcher;
import com.ecolink.core.map.util.ContentListFetcher;
import com.ecolink.core.map.util.StoreDetailFetcher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class StoreMapController {

	private final ContentListFetcher contentListFetcher;
	private final ContentDetailFetcher contentDetailFetcher;
	private final StoreDetailFetcher storeDetailFetcher;

	public StoreMapController(ContentListFetcher contentListFetcher, ContentDetailFetcher contentDetailFetcher,
		StoreDetailFetcher storeDetailFetcher) {
		this.contentListFetcher = contentListFetcher;
		this.contentDetailFetcher = contentDetailFetcher;
		this.storeDetailFetcher = storeDetailFetcher;
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

	@Tag(name = "${swagger.map-data}")
	@Operation(summary = "상점 데이터 등록 API", description = "상점 상세 데이터 조회 및 등록 API")
	@GetMapping("/store")
	public void getStoreDetailAll() {
		storeDetailFetcher.fetchAndStoreInfoDetail();
	}

}
