package com.ecolink.core.manager.dto.request;

import com.ecolink.core.common.constant.Address;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record StoreRegistrationRequest(
	@Schema(description = "입점할 매장 이름", example = "지팔샵")
	String name,
	@Schema(description = "입점할 매장 사업자등록번호", example = "12421-52363464-36436-52")
	String businessNumber,
	@Schema(description = "입점할 매장 연락처", example = "010-1234-5678")
	String contact,
	@Schema(description = "입점할 매장 대표명", example = "칼든강도")
	String representative,
	@Schema(description = "입점할 매장 주소")
	Address address
) {
}
