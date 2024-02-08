package com.ecolink.core.event.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddEventRequest {

	@Schema(description = "이벤트 타이틀", example = "병뚜껑으로 악세서리 만들기")
	@NotNull
	@Size(min = 2, max = 30, message = "타이틀은 공백 포함 최소 2자에서 최대 30자까지 입력 가능합니다.")
	private String title;

	@Schema(description = "이벤트 시작 날짜", example = "2024-02-07")
	@NotNull
	private LocalDate startDate;

	@Schema(description = "이벤트 종료 날짜", example = "2024-03-07")
	@NotNull
	private LocalDate endDate;

	@Schema(description = "이벤트 신청 링크", example = "http://~")
	private String applyUrl;

	@Schema(description = "이벤트 내용", example = "뚜껑 버리지 말고 모아서 예쁜 악세서리 만들어요~!")
	@NotNull
	@Size(min = 10, max = 100, message = "텍스트는 공백 포함 최소 10자에서 최대 100자까지 입력 가능합니다.")
	private String text;
}
