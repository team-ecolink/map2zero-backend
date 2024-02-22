package com.ecolink.core.tag.dto.response;

import com.ecolink.core.tag.constant.TagCategory;
import com.ecolink.core.tag.domain.Tag;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GetTagResponse(
	@Schema(description = "태그 ID", example = "10")
	Long id,
	@Schema(description = "태그 이름", example = "주방용품")
	String name,
	@Schema(description = "태그 컬러(미구현)")
	String color,
	@Schema(description = "태그 카테고리", example = "PRODUCT")
	TagCategory category
) {

	public static GetTagResponse of(Tag tag) {
		return new GetTagResponse(tag.getId(), tag.getName(), tag.getColor(), tag.getCategory());
	}

}
