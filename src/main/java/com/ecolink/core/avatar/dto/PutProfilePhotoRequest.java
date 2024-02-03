package com.ecolink.core.avatar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PutProfilePhotoRequest {
	@Schema(description = "기본 사진으로 변경 여부 true일시 기본 사진으로 전환", example = "true")
	@JsonProperty(value = "default")
	@NotNull
	private boolean defaultPhoto;
}
