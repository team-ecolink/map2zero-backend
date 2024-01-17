package com.ecolink.core.health.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HealthCheckResponse {

	@Schema(description = "서버 health 상태", example = "ok")
	private final String health;

	@Schema(description = "현재 실행 중인 profile", example = "[dev]")
	private final List<String> activeProfiles;

	@Schema(description = "현재 요청 서버 시간", example = "2023-01-23 16:31:04")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private final LocalDateTime currentTimeStamp;

	private HealthCheckResponse(String health, List<String> activeProfiles, LocalDateTime currentTimeStamp) {
		this.health = health;
		this.activeProfiles = activeProfiles;
		this.currentTimeStamp = currentTimeStamp;
	}

	public static HealthCheckResponse ok(List<String> activeProfiles) {
		return new HealthCheckResponse("ok", activeProfiles, LocalDateTime.now());
	}

}
