package com.ecolink.core.avatar.dto.response;

import java.time.LocalDateTime;

import com.ecolink.core.bookmark.dto.response.StoreInfoDto;
import com.ecolink.core.common.domain.ImageFile;
import com.ecolink.core.review.domain.Review;
import com.ecolink.core.store.domain.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MyPageReviewResponse {
	@Schema(description = "리뷰 아이디", example = "1")
	private final Long id;
	@Schema(description = "리뷰 내용", example = "매장이 깔끔해요")
	private final String text;
	@Schema(description = "리뷰 작성 시간")
	private final LocalDateTime createdDate;
	@Schema(description = "리뷰 점수", example = "4")
	private final int score;
	@Schema(description = "리뷰 좋아요 갯수", example = "8")
	private final int likeCnt;
	@Schema(description = "리뷰한 매장 정보", example = "8")
	private final StoreInfoDto store;
	@Schema(description = "리뷰 첫 번째 사진 리뷰 사진이 없을 시 null")
	private final ImageFile photo;
	@Schema(description = "리뷰 작성 여부", example = "true")
	@JsonProperty("isWriter")
	private final boolean isWriter;
	@Schema(description = "리뷰 좋아요 여부", example = "false")
	private final boolean isLiked;

	@QueryProjection
	public MyPageReviewResponse(Review review, ImageFile photo, Store store, boolean isWriter, boolean isLiked) {
		this.id = review.getId();
		this.text = review.getText();
		this.createdDate = review.getCreatedDate();
		this.score = review.getScore();
		this.likeCnt = review.getLikeCnt();
		this.photo = photo;
		this.store = StoreInfoDto.of(store);
		this.isWriter = isWriter;
		this.isLiked = isLiked;
	}

}
