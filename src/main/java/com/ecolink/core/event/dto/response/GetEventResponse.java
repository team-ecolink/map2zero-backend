package com.ecolink.core.event.dto.response;

import java.time.LocalDate;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.common.error.ErrorCode;
import com.ecolink.core.common.error.exception.EntityNotFoundException;
import com.ecolink.core.event.domain.Event;
import com.ecolink.core.event.domain.EventPhoto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GetEventResponse(
	@Schema(description = "이벤트 ID", example = "1")
	Long id,
	@Schema(description = "이벤트 이름", example = "이벤트1")
	String title,
	@Schema(description = "시작 시간", example = "2024-01-01")
	LocalDate startDate,
	@Schema(description = "종료 시간", example = "2024-01-31")
	LocalDate endDate,
	@Schema(description = "대표 사진")
	ImageFile photo
) {
	public static GetEventResponse of(Event event) {
		return GetEventResponse.builder()
			.id(event.getId())
			.title(event.getTitle())
			.startDate(event.getStartDate().toLocalDate())
			.endDate(event.getEndDate().toLocalDate())
			.photo(event.getEventPhotos().stream()
				.filter(eventPhoto -> eventPhoto.getGivenOrder() == 0)
				.map(EventPhoto::getFile).findFirst().orElseThrow(
					() -> new EntityNotFoundException(ErrorCode.IMAGE_NOT_FOUND)))
			.build();
	}
}
