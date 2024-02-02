package com.ecolink.core.event.dto.response;

import java.time.LocalDateTime;

import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.event.domain.Event;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetEventResponse {
	@Schema(description = "이벤트 ID", example = "1")
	private final Long id;
	@Schema(description = "이벤트 이름", example = "이벤트1")
	private final String title;
	@Schema(description = "시작 시간", example = "2024-01-01")
	private final LocalDateTime startDate;
	@Schema(description = "종료 시간", example = "2024-01-31")
	private final LocalDateTime endDate;
	@Schema(description = "진행중 여부", example = "ACTIVE")
	private final String status;
	@Schema(description = "대표 사진")
	private final ImageFile photo;

	@QueryProjection
	public GetEventResponse(Event event, ImageFile photo) {
		this.id = event.getId();
		this.title = event.getTitle();
		this.startDate = event.getStartDate();
		this.endDate = event.getEndDate();
		this.status = event.getStatus().toString();
		this.photo = photo;
	}
}
