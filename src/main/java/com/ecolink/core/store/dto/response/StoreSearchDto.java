package com.ecolink.core.store.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StorePhoto;
import com.ecolink.core.store.dto.StoreProductDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreSearchDto {

	@Schema(description = "매장 ID", example = "1")
	private final Long id;

	@Schema(description = "매장 이름", example = "지구샵 연남점")
	private final String name;

	@Schema(description = "매장 주소")
	private final Address address;

	@Schema(description = "매장 총점", example = "4.8")
	private final double totalScore;

	@Schema(description = "매장 리뷰 수", example = "50")
	private final int reviewCnt;

	@Schema(description = "매장 북마크 수", example = "30")
	private final int bookmarkCnt;

	@Schema(description = "매장 대표 사진")
	private final ImageFile photo;

	@Schema(description = "매장 상품 이름 (최대 3가지)")
	private final List<StoreProductDto> products;

	@Schema(description = "해당 유저의 매장 북마크 여부", example = "true")
	private final boolean bookmarked;

	@QueryProjection
	public StoreSearchDto(Store store, StorePhoto storePhoto, boolean bookmarked) {
		this.id = store.getId();
		this.name = store.getName();
		this.address = store.getAddress();
		this.totalScore = store.getTotalScore();
		this.reviewCnt = store.getReviewCnt();
		this.bookmarkCnt = store.getBookmarkCnt();
		this.photo = storePhoto != null ? storePhoto.getFile() : null;
		this.bookmarked = bookmarked;
		this.products = new ArrayList<>();
	}

	public void addStoreProductDto(StoreProductDto dto) {
		this.products.add(dto);
	}
}
