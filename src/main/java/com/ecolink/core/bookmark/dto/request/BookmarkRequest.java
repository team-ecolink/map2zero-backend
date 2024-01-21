package com.ecolink.core.bookmark.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookmarkRequest(
	@Schema(description = "Avatar ID", example = "1")
	Long avatarId,
	@Schema(description = "Store ID", example = "1")
	Long storeId
) {
}
