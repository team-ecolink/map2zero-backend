package com.ecolink.core.store.dto;


import java.time.LocalTime;

import com.ecolink.core.store.constant.DayOfWeek;
import com.ecolink.core.store.domain.StoreOperatingHours;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OperatingHourDto {

	@Schema(description = "요일", example = "월")
	DayOfWeek dayOfWeek;
	@Schema(description = "영업 시작 시각", example = "11:00")
	LocalTime startTime;
	@Schema(description = "영업 종료 시각", example = "21:00")
	LocalTime endTime;
	@Schema(description = "정기 휴무 여부", example = "true")
	boolean regularHoliday;

	public static OperatingHourDto of(StoreOperatingHours operatingHours) {
		return OperatingHourDto.builder()
					.dayOfWeek(operatingHours.getDayOfWeek())
					.startTime(operatingHours.getStartTime())
					.endTime(operatingHours.getEndTime())
					.regularHoliday(operatingHours.isRegularHoliday())
					.build();
	}
}
