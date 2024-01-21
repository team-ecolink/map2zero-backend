package com.ecolink.core.bookmark.dto.response;

import com.ecolink.core.bookmark.domain.Bookmark;
import com.ecolink.core.store.domain.Store;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookmarkResponse(
	@Schema(description = "북마크 ID", example = "1")
	Long id,
	@Schema(description = "매장 정보")
	StoreInfo storeInfo
) {

	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public record StoreInfo(
		@Schema(description = "매장 ID", example = "1")
		Long id,
		@Schema(description = "매장 이름", example = "Eco-link Store")
		String name
	) {

		public static StoreInfo of(Store store) {
			return new StoreInfo(
				store.getId(),
				store.getName()
			);
		}
	}

	public static BookmarkResponse from(Bookmark bookmark) {
		return new BookmarkResponse(
			bookmark.getId(),
			bookmark.getStore() != null ? StoreInfo.of(bookmark.getStore()) : null
		);
	}
}
