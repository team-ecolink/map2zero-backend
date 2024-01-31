package com.ecolink.core.review.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.domain.ReviewPhoto;
import com.ecolink.core.review.dto.ReviewTagDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetReviewResponse{
	@Schema(description = "리뷰 아이디", example = "1")
	private Long id;
	@Schema(description = "리뷰 내용", example = "매장이 깔끔해요")
	private String text;
	@Schema(description = "작성자", example = "홍길동")
	private String nickname;
	@Schema(description = "작성 시간", example = "2024-01-01")
	private LocalDate createdDate;
	@Schema(description = "점수", example = "4")
	private int score;
	@Schema(description = "좋아요 갯수", example = "8")
	private int likeCnt;
	@Schema(description = "좋아요 여부", example = "true")
	private boolean isLiked;
	@Schema(description = "작성 여부", example = "false")
	private boolean isWriter;
	@Schema(description = "리뷰 사진")
	private List<ImageFile> reviewPhotos;
	@Schema(description = "리뷰 태그")
	private List<ReviewTagDto> reviewTags;

	public GetReviewResponse setLiked(boolean isLikedParam) {
		isLiked = isLikedParam;
		return this;
	}

	public static GetReviewResponse of(Review review, boolean isLiked, boolean isWriter) {
		return GetReviewResponse.builder()
			.id(review.getId())
			.text(review.getText())
			.score(review.getScore())
			.nickname(review.getWriter().getNickname())
			.createdDate(review.getCreatedDate().toLocalDate())
			.likeCnt(review.getLikeCnt())
			.isLiked(isLiked)
			.isWriter(isWriter)
			.reviewPhotos(review.getReviewPhotos().stream()
				.map(ReviewPhoto::getFile).toList())
			.reviewTags(review.getReviewTags().stream()
				.map(ReviewTagDto::of).toList())
			.build();

	}
}
