package com.ecolink.core.common.constant;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Embeddable
public class Address {

	@Schema(description = "시/도 주소(1차)", example = "서울특별시")
	@NotBlank
	@NotNull
	private String province;

	@Schema(description = "시/군/구 주소(2차)", example = "동작구")
	@NotBlank
	@NotNull
	private String city;

	@Schema(description = "도로명 주소", example = "서달로12나길 2 1층")
	@NotBlank
	@NotNull
	private String roadName;

	@Schema(description = "지번 주소", example = "흑석동 54-149 1층")
	private String lotNumber;

}
