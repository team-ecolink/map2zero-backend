package com.ecolink.core.review.dto;

import java.time.LocalDate;
import java.util.List;

import com.ecolink.core.review.domain.Review;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewDto {

	@Schema(description = "리뷰 아이디", example = "id")
	Long id;
	@Schema(description = "닉네임", example = "구준표")
	String nickname;
	@Schema(description = "리뷰 점수", example = "4")
	Integer score;
	@Schema(description = "리뷰 내용", example = "올모스트 패러다이스")
	String text;
	@Schema(description = "리뷰 작성 시간", example = "2024-01-02")
	LocalDate createdDate;
	@Schema(description = "좋아요 갯수", example = "3")
	Integer likeCnt;
	@Schema(description = "리뷰 사진")
	List<ReviewPhotoDto> photos;
	@Schema(description = "리뷰 태그")
	List<ReviewTagDto> reviewTags;
	@Schema(description = "좋아요 여부", example = "true")
	Boolean isLiked;

	public static ReviewDto of(Review review, Boolean isLiked) {
		return ReviewDto.builder()
			.id(review.getId())
			.nickname(review.getWriter().getNickname())
			.text(review.getText())
			.score(review.getScore())
			.createdDate(review.getCreatedDate().toLocalDate())
			.likeCnt(review.getLikeCnt())
			.photos(review.getReviewPhotos().stream()
				.map(ReviewPhotoDto::of).toList())
			.reviewTags(review.getReviewTags().stream()
				.map(ReviewTagDto::of).toList())
			.isLiked(isLiked)
			.build();
	}
}
