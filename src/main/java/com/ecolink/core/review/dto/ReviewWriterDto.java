package com.ecolink.core.review.dto;

import com.ecolink.core.avatar.domain.Avatar;
import com.ecolink.core.common.domain.ImageFile;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReviewWriterDto(
	@Schema(description = "작성자 닉네임")
	String nickname,
	@Schema(description = "작성자 프로필 사진")
	ImageFile photo
) {

	public static ReviewWriterDto of(Avatar writer) {
		return new ReviewWriterDto(writer.getNickname(), writer.getPhoto().getFile());
	}

}
