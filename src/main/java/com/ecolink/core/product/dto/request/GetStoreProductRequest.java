package com.ecolink.core.product.dto.request;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetStoreProductRequest {

	@Parameter(description = "검색을 시작할 커서의 id(포함) 최초 조회시에는 입력하지 말아주세요")
	@Nullable
	@Positive
	private final Long cursor;

	@Parameter(description = "검색 사이즈")
	@NotNull
	@Range(min = 1, max = 30)
	private final Integer size;

	@Parameter(description = "검색어", example = "연필")
	@Nullable
	private final String keyword;

	@Parameter(name = "tag", description = "검색할 제품 태그(카테고리)의 ID", example = "6")
	@Nullable
	private final Long tagId;

	public GetStoreProductRequest(Long cursor, Integer size, String keyword, Long tag) {
		this.keyword = keyword;
		this.cursor = cursor;
		this.size = size;
		this.tagId = tag;
	}

}
