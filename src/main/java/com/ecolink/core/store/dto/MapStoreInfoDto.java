package com.ecolink.core.store.dto;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.store.domain.Store;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MapStoreInfoDto {

	@Schema(description = "매장 ID", example = "1")
	private final Long id;
	@Schema(description = "매장 상호명")
	private final String name;
	@Schema(description = "매장 주소")
	private final Address address;
	@Schema(description = "리뷰 수", example = "10")
	private final int reviewCnt;
	@Schema(description = "리뷰 평점", example = "4.4")
	private final double averageScore;
	@Schema(description = "경도", example = "126.9960")
	private final double x;
	@Schema(description = "위도", example = "37.5601")
	private final double y;
	@Schema(description = "매장 대표 사진")
	private final ImageFile photo;
	@Schema(description = "현재 위치로부터 거리(미터단위)", example = "1743")
	private final double distance;
	@Schema(description = "북마크 여부", example = "false")
	private final boolean isBookmarked;

	@QueryProjection
	public MapStoreInfoDto(Store store, ImageFile file, double distance, boolean isBookmarked) {
		this.id = store.getId();
		this.name = store.getName();
		this.address = store.getAddress();
		this.reviewCnt = store.getReviewCnt();
		this.averageScore = store.roundedAverageScore();
		this.x = roundCoordinate(store.getCoordinates().getX());
		this.y = roundCoordinate(store.getCoordinates().getY());
		this.photo = file;
		this.distance = Math.round(distance);
		this.isBookmarked = isBookmarked;
	}

	private double roundCoordinate(double coordinate) {
		return Math.round(coordinate * 1_000_000_000.0) / 1_000_000_000.0;
	}

}
