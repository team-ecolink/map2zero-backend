package com.ecolink.core.map.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ContentItemDto {
	@Schema(description = "등록일", example = "2023-10-31 10:04:58.0")
	private String cotRegDate;

	@Schema(description = "콘텐츠 테마 서브 ID", example = "4")
	private String cotThemeSubId;

	@Schema(description = "업데이트 일", example = "2023-10-31 13:12:19.0")
	private String cotUpdateDate;

	@Schema(description = "콘텐츠 테마 ID", example = "11103395")
	private String cotThemeId;

	@Schema(description = "콘텐츠 ID", example = "zerowaste_0005")
	private String cotContsId;

	@Schema(description = "콘텐츠 상태", example = "0")
	private String cotContsStat;
}
