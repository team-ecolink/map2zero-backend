package com.ecolink.core.review.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;

@Getter
public class ReviewSearchRequest {

	@Parameter(description = "페이지 번호", example = "0")
	private final Integer pageNo;
	@Parameter(description = "페이지 사이즈", example = "5")
	private final Integer size;
	@Parameter(description = "정렬 기준", example = "likeCnt")
	private final String criteria;

	public ReviewSearchRequest(Integer pageNo, Integer size, String criteria) {
		this.pageNo = pageNo;
		this.size = size;
		this.criteria = criteria;
	}
}
