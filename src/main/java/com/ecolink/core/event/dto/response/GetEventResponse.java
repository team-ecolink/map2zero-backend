package com.ecolink.core.event.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.event.domain.Event;
import com.ecolink.core.event.domain.EventPhoto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetEventResponse {

	@Schema(description = "이벤트 이름", example = "이벤트1")
	private final String title;
	@Schema(description = "이벤트 설명", example = "이벤트 설명")
	private final String description;
	@Schema(description = "이벤트 참여 링크", example = "https://www.~.com")
	private final String applicationUrl;
	@Schema(description = "시작 시간", example = "2024-01-01")
	private final LocalDate startDate;
	@Schema(description = "종료 시간", example = "2024-01-31")
	private final LocalDate endDate;
	@Schema(description = "진행중 여부", example = "ACTIVE")
	private final String status;
	@Schema(description = "이벤트 사진")
	private final List<ImageFile> photos;
	@Schema(description = "매니저인지 여부", example = "false")
	private final boolean isManager;

	public static GetEventResponse of(Event event, boolean isManager) {
		return GetEventResponse.builder()
			.title(event.getTitle())
			.description(event.getDescription())
			.applicationUrl(event.getApplicationUrl())
			.startDate(event.getStartDate())
			.endDate(event.getEndDate())
			.status(event.getStatus().toString())
			.photos(event.getEventPhotos().stream().map(EventPhoto::getFile).toList())
			.isManager(isManager)
			.build();
	}
}
