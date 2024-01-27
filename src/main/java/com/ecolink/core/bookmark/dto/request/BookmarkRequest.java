package com.ecolink.core.bookmark.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookmarkRequest(
	@Schema(description = "Store ID", example = "1")
	Long storeId
) {
}
