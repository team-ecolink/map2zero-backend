package com.ecolink.core.bookmark.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookmarkRequest(
	@Schema(description = "Store ID", example = "1")
	Long storeId
) {
}