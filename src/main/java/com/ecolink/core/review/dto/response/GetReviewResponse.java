package com.ecolink.core.review.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.review.domain.Review;
import com.ecolink.core.review.domain.ReviewPhoto;
import com.ecolink.core.review.dto.ReviewTagDto;
import com.ecolink.core.review.dto.ReviewWriterDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetReviewResponse {
	@Schema(description = "리뷰 아이디", example = "1")
	private final Long id;
	@Schema(description = "리뷰 내용", example = "매장이 깔끔해요")
	private final String text;
	@Schema(description = "작성 시간")
	private final LocalDateTime createdDate;
	@Schema(description = "점수", example = "4")
	private final int score;
	@Schema(description = "좋아요 갯수", example = "8")
	private final int likeCnt;
	@Schema(description = "작성 여부", example = "false")
	@JsonProperty("isWriter")
	private final boolean isWriter;
	@Schema(description = "작성자 정보")
	private final ReviewWriterDto writer;
	@Schema(description = "리뷰 사진")
	private final List<ImageFile> photos;
	@Schema(description = "리뷰 태그")
	private final List<ReviewTagDto> tags;
	@Schema(description = "좋아요 여부", example = "true")
	private boolean isLiked;

	public static GetReviewResponse of(Review review, boolean isWriter) {
		return GetReviewResponse.builder()
			.id(review.getId())
			.text(review.getText())
			.score(review.getScore())
			.createdDate(review.getCreatedDate())
			.likeCnt(review.getLikeCnt())
			.isWriter(isWriter)
			.writer(ReviewWriterDto.of(review.getWriter()))
			.photos(review.getReviewPhotos().stream()
				.map(ReviewPhoto::getFile).toList())
			.tags(review.getReviewTags().stream()
				.map(ReviewTagDto::of).toList())
			.isLiked(false)
			.build();

	}

	public void setLikedTrue() {
		isLiked = true;
	}

}
