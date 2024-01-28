package com.ecolink.core.store.dto.response;

import java.util.List;

import com.ecolink.core.store.domain.SearchHistory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchHistoryDto {

	@Schema(description = "검색 히스토리 ID", example = "1")
	private final Long id;

	@Schema(description = "검색 키워드", example = "지구샵")
	private final String keyword;

	public SearchHistoryDto(SearchHistory searchHistory) {
		this.id = searchHistory.getId();
		this.keyword = searchHistory.getWord();
	}

	public static List<SearchHistoryDto> of(List<SearchHistory> searchHistories) {
		return searchHistories.stream().map(SearchHistoryDto::new).toList();
	}
}
