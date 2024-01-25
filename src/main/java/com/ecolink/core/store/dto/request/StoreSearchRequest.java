package com.ecolink.core.store.dto.request;

import com.ecolink.core.store.constant.SearchType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreSearchRequest {

	@Parameter(description = "검색어", example = "지구샵")
	private final String keyword;

	@Parameter(description = "검색 기준 (STORE/PRODUCT)", example = "STORE")
	private final SearchType type;

	@Parameter(description = "이전 페이지의 마지막 매장 북마크 수", example = "92")
	private final Long cursor;

	@Parameter(description = "이전 페이지의 마지막 매장 id", example = "1")
	private final Long storeId;

	@Parameter(description = "페이지당 결과의 개수", example = "5")
	private final int pageSize;

	public StoreSearchRequest(String keyword, SearchType type, Long cursor, Long storeId, int pageSize) {
		this.keyword = keyword;
		this.type = type;
		this.cursor = cursor;
		this.storeId = storeId;
		this.pageSize = pageSize;
	}
}
