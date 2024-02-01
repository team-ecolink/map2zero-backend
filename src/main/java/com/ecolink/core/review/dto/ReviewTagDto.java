package com.ecolink.core.review.dto;

import com.ecolink.core.review.domain.ReviewTag;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReviewTagDto(
	@Schema(description = "태그 아이디", example = "1")
	Long id,
	@Schema(description = "태그 이름", example = "친절해요")
	String name
) {

	public static ReviewTagDto of(ReviewTag reviewTag) {
		return new ReviewTagDto(reviewTag.getTag().getId(), reviewTag.getTag().getName());
	}

}
