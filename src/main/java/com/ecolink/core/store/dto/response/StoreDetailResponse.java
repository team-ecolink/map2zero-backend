package com.ecolink.core.store.dto.response;

import java.util.List;

import com.ecolink.core.common.constant.Address;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.domain.StorePhoto;
import com.ecolink.core.store.dto.OperatingHourDto;
import com.ecolink.core.store.dto.StoreTagDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record StoreDetailResponse(
	@Schema(description = "매장 ID", example = "1")
	Long id,
	@Schema(description = "매장 이름", example = "지구샵 연남점")
	String name,
	@Schema(description = "매장 설명", example = "지구를 위한 첫걸음, 지구샵입니다.")
	String description,
	@Schema(description = "연락처", example = "02-111-1111")
	String contact,
	@Schema(description = "홈페이지 URL", example = "https://www.~~~.com")
	String homepageUrl,
	@Schema(description = "인스타그램 URL", example = "https://www.instagram.~~~.com")
	String instagramUrl,
	@Schema(description = "네이버 지도 URL", example = "https://www.map.naver.~~~.com")
	String naverMapUrl,
	@Schema(description = "매장 북마크 수", example = "100")
	int bookmarkCnt,
	@Schema(description = "매장 리뷰 수", example = "5")
	int reviewCnt,
	@Schema(description = "매장 평점", example = "4.8")
	double averageScore,
	@Schema(description = "판매중인 상품 수", example = "30")
	long storeProductCnt,
	@Schema(description = "매장 주소")
	Address address,
	@Schema(description = "매장 사진")
	List<ImageFile> photos,
	@Schema(description = "매장 영업 시간")
	List<OperatingHourDto> operatingHours,
	@Schema(description = "시설물 태그(아이콘)")
	List<StoreTagDto> storeTags,
	@Schema(description = "북마크 여부", example = "true")
	boolean isBookmarked
) {

	public static StoreDetailResponse of(Store store, Long productCnt, boolean isBookmarked) {
		return StoreDetailResponse.builder()
			.id(store.getId())
			.name(store.getName())
			.description(store.getDescription())
			.contact(store.getContact())
			.homepageUrl(store.getHomepageUrl())
			.instagramUrl(store.getInstagramUrl())
			.naverMapUrl(store.getNaverMapUrl())
			.bookmarkCnt(store.getBookmarkCnt())
			.reviewCnt(store.getReviewCnt())
			.storeProductCnt(productCnt)
			.averageScore(store.roundedAverageScore())
			.address(store.getAddress())
			.photos(store.getStorePhotos().stream()
				.map(StorePhoto::getFile).toList())
			.operatingHours(store.getStoreOperatingHour().stream()
				.map(OperatingHourDto::of).toList())
			.storeTags(store.getStoreTags().stream()
				.map(StoreTagDto::of).toList())
			.isBookmarked(isBookmarked)
			.build();
	}

}
