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

	@Parameter(description = "시작할 커서(포함) 값이 없으면 처음부터 조회합니다.")
	private final Long cursor;

	@Parameter(description = "페이지당 결과의 개수", example = "5")
	private final int size;

	public StoreSearchRequest(String keyword, SearchType type, Long cursor, int size) {
		this.keyword = keyword;
		this.type = type;
		this.cursor = cursor;
		this.size = size;
	}
}
