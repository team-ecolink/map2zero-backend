package com.ecolink.core.review.dto.request;

import java.util.List;

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
public class CreateReviewRequest {

	@Schema(description = "매장 ID", example = "1")
	@NotNull
	private Long storeId;

	@Schema(description = "태그 ID 리스트", example = "[6, 7]")
	private List<Long> tagIds;

	@Schema(description = "리뷰 별점", example = "5")
	@NotNull
	@Min(value = 1, message = "별점은 1 이상이어야 합니다.")
	@Max(value = 5, message = "별점은 5 이하여야 합니다.")
	private int score;

	@Schema(description = "리뷰 내용", example = "사장님이 친절하시고 매장이 깔끔해요!!")
	@NotNull
	@Size(min = 10, max = 100, message = "텍스트는 최소 10자에서 최대 100자까지 입력 가능합니다.")
	private String text;

}
