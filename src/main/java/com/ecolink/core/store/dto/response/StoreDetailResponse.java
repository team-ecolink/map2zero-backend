package com.ecolink.core.store.dto.response;

import java.util.List;

import com.ecolink.core.event.dto.EventDto;
import com.ecolink.core.review.dto.ReviewDto;
import com.ecolink.core.store.domain.Store;
import com.ecolink.core.store.dto.StorePhotoDto;
import com.ecolink.core.store.dto.ProductDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreDetailResponse {
	@Schema(description = "상호명", example = "지구샵")
	String name;
	@Schema(description = "매장 설명", example = "지구를 위한 첫걸음, 지구샵입니다.")
	String description;
	@Schema(description = "홈페이지 URL", example = "https://www.~~~.com")
	String homepageUrl;
	@Schema(description = "인스타그램 URL", example = "https://www.instagram.~~~.com")
	String instagramUrl;
	@Schema(description = "북마크 수", example = "100")
	Integer bookmarkCnt;
	@Schema(description = "리뷰 수", example = "5")
	Integer reviewCnt;
	@Schema(description = "리뷰 점수", example = "4.5")
	Double score;
	@Schema(description = "매장 사진")
	List<StorePhotoDto> photos;
	@Schema(description = "매장 제품 리스트")
	List<ProductDto> products;
	@Schema(description = "이벤트 리스트")
	List<EventDto> events;
	@Schema(description = "리뷰 리스트")
	List<ReviewDto> reviews;
	@Schema(description = "북마크 여부", example = "true")
	Boolean isBookmarked;

	public static StoreDetailResponse of(Store store, List<ReviewDto> reviewList, Boolean isBookmarked) {
		return StoreDetailResponse.builder()
			.name(store.getName())
			.description(store.getDescription())
			.instagramUrl(store.getInstagramUrl())
			.homepageUrl(store.getHomepageUrl())
			.bookmarkCnt(store.getBookmarkCnt())
			.reviewCnt(store.getReviewCnt())
			.photos(store.getStorePhotos().stream()
				.map(StorePhotoDto::of).toList())
			.products(store.getStoreProduct().stream()
				.map(ProductDto::of).toList())
			.events(store.getEvents().stream()
				.map(EventDto::of).toList())
			.reviews(reviewList)
			.isBookmarked(isBookmarked)
			.build();
	}
}
