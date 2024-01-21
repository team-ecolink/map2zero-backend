package com.ecolink.core.event.dto;

import java.time.LocalDateTime;

import com.ecolink.core.event.domain.Event;
import com.ecolink.core.event.domain.EventPhoto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EventDto {

	@Schema(description = "이벤트 아이디", example = "1")
	Long id;
	@Schema(description = "이벤트 타이틀", example = "이벤트 1")
	String title;
	@Schema(description = "이벤트 시작 일시", example = "2024-01-01 11:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	LocalDateTime startDate;
	@Schema(description = "이벤트 종료 일시", example = "2024-01-31 23:59:59")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	LocalDateTime endDate;
	@Schema(description = "이벤트 대표 사진", example = "https://www.~~~.com")
	String eventPhoto;

	public static EventDto of(Event event) {
		return EventDto.builder()
			.id(event.getId())
			.title(event.getTitle())
			.startDate(event.getStartDate())
			.endDate(event.getEndDate())
			.eventPhoto(event.getEventPhotos().stream()
				.filter(photo -> photo.getGivenOrder() == 1) // 첫 번째 사진만 가져오기
				.map(photo-> photo.getFile().getUrl()).toString())
			.build();
	}
}
