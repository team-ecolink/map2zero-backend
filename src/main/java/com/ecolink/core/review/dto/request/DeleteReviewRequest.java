package com.ecolink.core.review.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeleteReviewRequest {

	@Schema(description = "삭제할 리뷰 ID", example = "10")
	@Positive
	@NotNull
	private final Long reviewId;

	@JsonCreator
	public DeleteReviewRequest(Long reviewId) {
		this.reviewId = reviewId;
	}

}
