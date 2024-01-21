package com.ecolink.core.review.dto;

import com.ecolink.core.review.domain.ReviewPhoto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReviewPhotoDto {

	@Schema(description = "사진 URL", example = "https://www.~~~.com")
	String url;
	@Schema(description = "사진 순서", example = "1")
	Integer givenOrder;

	public ReviewPhotoDto(String url, Integer givenOrder) {
		this.url = url;
		this.givenOrder = givenOrder;
	}

	public static ReviewPhotoDto of(ReviewPhoto reviewPhoto) {
		return new ReviewPhotoDto(reviewPhoto.getFile().getUrl(), reviewPhoto.getGivenOrder());
	}
}
