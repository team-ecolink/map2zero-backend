package com.ecolink.core.map.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ContentListDetailDto {
	@Schema(description = "성공 오류 응답 코드", example = "0")
	private String retCode;

	@Schema(description = "데이터 양", example = "99")
	private int dataCount;

	@Schema(description = "콘텐츠 상세 정보")
	private List<ContentDetailDto> body;
}