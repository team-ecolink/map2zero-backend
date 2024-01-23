package com.ecolink.core.store.dto.response;

import java.util.List;

import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.AddressDto;
import com.ecolink.core.store.dto.OperatingHourDto;
import com.ecolink.core.store.dto.StorePhotoDto;
import com.ecolink.core.store.dto.StoreTagDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record StoreDetailResponse(
	@Schema(description = "상호명", example = "지구샵")
	String name,
	@Schema(description = "매장 설명", example = "지구를 위한 첫걸음, 지구샵입니다.")
	String description,
	@Schema(description = "홈페이지 URL", example = "https://www.~~~.com")
	String homepageUrl,
	@Schema(description = "인스타그램 URL", example = "https://www.instagram.~~~.com")
	String instagramUrl,
	@Schema(description = "북마크 수", example = "100")
	Integer bookmarkCnt,
	@Schema(description = "리뷰 수", example = "5")
	Integer reviewCnt,
	@Schema(description = "리뷰 점수", example = "4.5")
	Double score,
	@Schema(description = "매장 사진")
	List<StorePhotoDto> photos,
	@Schema(description = "연락처", example = "02-111-1111")
	String contact,
	@Schema(description = "매장 주소")
	AddressDto address,
	@Schema(description = "매장 영업 시간")
	List<OperatingHourDto> operatingHours,
	@Schema(description = "시설물 태그(아이콘)")
	List<StoreTagDto> storeTags,
	@Schema(description = "북마크 여부", example = "true")
	Boolean isBookmarked
) {
	public static StoreDetailResponse of(Store store, Boolean isBookmarked) {
		return StoreDetailResponse.builder()
			.name(store.getName())
			.description(store.getDescription())
			.instagramUrl(store.getInstagramUrl())
			.homepageUrl(store.getHomepageUrl())
			.bookmarkCnt(store.getBookmarkCnt())
			.reviewCnt(store.getReviewCnt())
			.photos(store.getStorePhotos().stream()
				.map(StorePhotoDto::of).toList())
			.contact(store.getContact())
			.address(AddressDto.of(store))
			.operatingHours(store.getStoreOperatingHour().stream()
				.map(OperatingHourDto::of).toList())
			.storeTags(store.getStoreTags().stream()
				.map(StoreTagDto::of).toList())
			.isBookmarked(isBookmarked)
			.build();
	}
}
