package com.ecolink.core.like.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PostLikeRequest(
	@Positive
	@NotNull
	@Schema(description = "리뷰 ID", example = "1")
	Long reviewId
) {
}
