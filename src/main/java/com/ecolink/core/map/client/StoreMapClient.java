package com.ecolink.core.map.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SeoulMapOpenFeign", url = "https://map.seoul.go.kr/smgis/apps/theme.do")
public interface StoreMapClient {

	@GetMapping(produces = "application/json")
	String getContentsListAll(
		@RequestParam("cmd") String cmd,
		@RequestParam("key") String key,
		@RequestParam("theme_id") String themeId
	);
}

