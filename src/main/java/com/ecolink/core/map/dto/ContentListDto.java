package com.ecolink.core.map.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ContentListDto {

	@Schema(description = "성공 오류 응답 코드", example = "0")
	private String retCode;

	@Schema(description = "데이터 양", example = "99")
	private int dataCount;

	@Schema(description = "테마 ID", example = "1")
	private List<String> cotThemeId;

	@Schema(description = "콘텐츠 서브카테고리 ID", example = "1")
	private List<String> cotThemeSubId;

	@Schema(description = "콘텐츠 ID", example = "zerowaste_0005")
	private List<String> cotContsId;

	@Schema(description = "콘텐츠 사용유무", example = "0")
	private List<String> cotContsStat;

	@Schema(description = "업데이트일", example = "2023-10-31 10:04:58.0")
	private List<String> cotUpdateDate;

	@Schema(description = "등록일", example = "2023-10-31 10:04:58.0")
	private List<String> cotRegDate;

}
