package com.ecolink.core.store.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;

@Getter
public class StoreSearchRequest {

	@Parameter(description = "검색어", example = "지구샵")
	private final String keyword;

	@Parameter(description = "검색 기준 (매장/제품)", example = "매장")
	private final String type;

	public StoreSearchRequest(String keyword, String type) {
		this.keyword = keyword;
		this.type = type;
	}
}
