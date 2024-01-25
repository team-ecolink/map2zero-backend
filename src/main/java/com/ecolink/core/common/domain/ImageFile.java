package com.ecolink.core.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Embeddable
public class ImageFile {

	@NotNull
	private String url;

	@JsonIgnore
	private String s3Key;

	@JsonIgnore
	private Long byteSize;

	private Integer width;

	private Integer height;

	private ImageFile(String url, String s3Key, Long byteSize, Integer width, Integer height) {
		this.url = url;
		this.s3Key = s3Key;
		this.byteSize = byteSize;
		this.width = width;
		this.height = height;
	}

	public static ImageFile externalImage(String url) {
		return new ImageFile(url, null, null, null, null);
	}

}
