package com.ecolink.core.common.domain;

import com.ecolink.core.file.dto.FileInfo;
import com.ecolink.core.file.dto.ImageSize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
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

	@Schema(description = "이미지 파일의 URL", example = "https://www.~~~.com")
	@NotNull
	private String url;

	@JsonIgnore
	private String s3Key;

	@JsonIgnore
	private Long byteSize;

	@Schema(description = "이미지 파일의 width (픽셀 단위)", example = "320")
	private Integer width;

	@Schema(description = "이미지 파일의 height (픽셀 단위)", example = "320")
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

	public static ImageFile of(FileInfo info, ImageSize size) {
		return new ImageFile(info.url(), info.key(), size.byteSize(), size.width(), size.height());
	}
}
