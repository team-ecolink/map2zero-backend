package com.ecolink.core.store.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;

@Getter
public class StoreSearchRequest {

	@Parameter(description = "검색어", example = "지구샵")
	private final String keyword;

	@Parameter(description = "검색 기준 (매장/제품)", example = "매장")
	private final String type;

	@Parameter(description = "이전 페이지의 마지막 매장 북마크 수", example = "92")
	private final Long cursorId;

	@Parameter(description = "페이지당 결과의 개수", example = "5")
	private final int pageSize;

	public StoreSearchRequest(String keyword, String type, Long cursorId, int pageSize) {
		this.keyword = keyword;
		this.type = type;
		this.cursorId = cursorId;
		this.pageSize = pageSize;
	}
}
