package com.ecolink.core.product.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostStoreProductRequest {

	@Schema(description = "품목명 최소 2자, 최대 20자", example = "연필")
	@Size(min = 2, max = 20)
	private String name;

	@Schema(description = "카테고리(태그)의 ID 최소, 최대 1개", example = "10")
	@NotNull
	private Long tagId;

	@Schema(description = "제품 가격 (10원 단위) 최소 10원, 최대 100,000원", example = "1000")
	@NotNull
	@Min(0)
	@Max(100_000)
	private int price;

}
