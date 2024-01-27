package com.ecolink.core.bookmark.dto.response;

import com.ecolink.core.bookmark.domain.Bookmark;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookmarkResponse(
	@Schema(description = "북마크 ID", example = "1")
	Long id,
	@Schema(description = "매장 정보")
	StoreInfoDto storeInfoDto
) {

	public static BookmarkResponse of(Bookmark bookmark) {
		return new BookmarkResponse(
			bookmark.getId(),
			bookmark.getStore() != null ? StoreInfoDto.of(bookmark.getStore()) : null
		);
	}
}
