package com.ecolink.core.review.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.dto.ReviewPhotoDto;
import com.ecolink.core.review.dto.ReviewTagDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReviewResponse(
	@Schema(description = "리뷰 내용", example = "매장이 깔끔해요")
	String text,
	@Schema(description = "작성자", example = "홍길동")
	String nickname,
	@Schema(description = "작성 시간", example = "2024-01-01")
	LocalDate createdAt,
	@Schema(description = "점수", example = "4")
	Integer score,
	@Schema(description = "좋아요 갯수", example = "8")
	Integer likeCnt,
	@Schema(description = "좋아요 여부", example = "true")
	Boolean isLiked,
	@Schema(description = "작성 여부", example = "false")
	Boolean isWriter,
	@Schema(description = "리뷰 사진")
	List<ReviewPhotoDto> reviewPhotos,
	@Schema(description = "리뷰 태그")
	List<ReviewTagDto> reviewTags

) {

	public static ReviewResponse of(Review review, Boolean isLiked, Boolean isWriter) {
		return ReviewResponse.builder()
			.text(review.getText())
			.score(review.getScore())
			.nickname(review.getWriter().getNickname())
			.createdAt(review.getCreatedDate().toLocalDate())
			.likeCnt(review.getLikeCnt())
			.isLiked(isLiked)
			.isWriter(isWriter)
			.reviewPhotos(review.getReviewPhotos().stream()
				.map(ReviewPhotoDto::of).toList())
			.reviewTags(review.getReviewTags().stream()
				.map(ReviewTagDto::of).toList())
			.build();

	}
}
