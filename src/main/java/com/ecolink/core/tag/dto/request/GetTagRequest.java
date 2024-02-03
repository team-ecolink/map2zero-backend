package com.ecolink.core.tag.dto.request;

import com.ecolink.core.tag.constant.TagCategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GetTagRequest(
	@Parameter(description = "조회할 태그의 카테고리", example = "ALL")
	@NotNull
	TagCategory category
) {

}
