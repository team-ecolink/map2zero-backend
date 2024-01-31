package com.ecolink.core.store.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchHistoryRequest {

	@Schema(description = "검색 히스토리 ID", example = "1")
	private Long searchHistoryId;

}
